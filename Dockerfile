#
# Set a variable that can be used in all stages.
#
ARG BUILD_HOME=/app

#
# Gradle image for the build stage.
#
FROM gradle:alpine as build-image

#
# Set the working directory.
#
ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
WORKDIR $APP_HOME

#
# Copy the Gradle config, source code, and static analysis config
# into the build container.
#
COPY --chown=gradle:gradle settings.gradle gradle.properties $APP_HOME/
COPY --chown=gradle:gradle buildSrc $APP_HOME/buildSrc
COPY --chown=gradle:gradle src $APP_HOME/src
#COPY --chown=gradle:gradle config $APP_HOME/config

#
# Build the application.
#
RUN gradle --no-daemon build

#
# Java image for the application to run in.
#
# Alternatives:
# eclipse-temurin:17-alpine
# amazoncorretto:20-alpine
# amazoncorretto:17-alpine
# 
FROM eclipse-temurin:20-alpine

#
# Copy the jar file in and name it app.jar.
#
ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
# src/$MODULE$/build/$MODULE$.jar
COPY --from=build-image $APP_HOME/src/*/build/libs/*.jar $APP_HOME/

#
# The command to run when the container starts.
#
RUN cd $APP_HOME
ENTRYPOINT cd $APP_HOME && java -jar *
