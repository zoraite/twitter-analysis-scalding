#!/bin/bash

BIN="../../scalding/scripts/scald.rb"
JOBS="../src/main/scala/br/ufc/scalding/jobs/"

INPUT="/output-brasil/brasil-tweets-state.tsv"
OUTPUT="/output-brasil"

for JOB in "ODMatrixJob"
do
    ${BIN} "--local" ${JOBS}""${JOB}".scala" "--input "${INPUT} "--output" ${OUTPUT}"/"${JOB}
done
