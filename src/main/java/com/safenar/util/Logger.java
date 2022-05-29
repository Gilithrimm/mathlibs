package com.safenar.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Logger {
    private Writer writer;
    private OutputStream stream;

    public Logger(OutputStream stream) {
        this.stream=stream;
    }

    public Logger(Writer writer) {
        this.writer = writer;
    }

    public void log(int level, String msg) {
        log(LogLevels.getLevel(level),msg);
    }

    public void log(LogLevels level, String msg){
        msg=level.toString() + ": " + msg+"\n";
        try {
            writer.write(msg);
        } catch (IOException e) {
            stackTrace(e);
        }catch (NullPointerException e){
            try {
                stream.write(msg.getBytes(StandardCharsets.UTF_8));
            } catch (IOException ex) {
                stackTrace(ex);
            }
        }
    }

    public void stackTrace(Throwable t){
        t.printStackTrace();
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public OutputStream getStream() {
        return stream;
    }

    public void setStream(OutputStream stream) {
        this.stream = stream;
    }
}
