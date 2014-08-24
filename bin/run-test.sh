#!/bin/sh

BIN="../../scalding/scripts/scald.rb"
JOBS="../src/main/scala/br/ufc/scalding/jobs/"

INPUT="../../data/brasil-tweets-state-sample.tsv"
OUTPUT="../../results"

for JOB in "ODMatrixJob"
do
    ${BIN} "--local" ${JOBS}""${JOB}".scala" "--input "${INPUT} "--output" ${OUTPUT}"/"${JOB}
done
