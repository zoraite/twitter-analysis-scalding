#!/bin/bash

BIN="hadoop"
JAR="../target/scala-2.10/twitter-analysis-scalding-0.0.1.jar"
JOBS="br.ufc.scalding.jobs"
INPUT="/output-brasil/brasil-tweets-state.tsv"
OUTPUT="/output-brasil/"

for JOB in "ODMatrixJob"
do
    ${BIN} jar ${JAR} \
    com.twitter.scalding.Tool \
    -D mapred.output.compress=false  \
    -D mapred.child.java.opts=-Xmx2048m \
    -D mapred.reduce.tasks=20 \
    ${JOBS}"."${JOB} \
    --hdfs \
    --input ${INPUT} \
    --output ${OUTPUT}${JOB}
done

