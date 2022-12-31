#!/usr/bin/env sh

rm -r docs/
# shellcheck disable=SC2035
javadoc -protected -splitindex -author -version -d docs/ -tag apiNote:a:"API Note:" -tag implSpec:a:"Implementation Requirements:" -tag implNote:a:"Implementation Note:" -sourcepath src/main/java -subpackages com.safenar
