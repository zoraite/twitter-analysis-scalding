#!/bin/sh

BIN="hadoop"
JAR="../target/scala-2.10/twitter-analysis-scalding-0.0.1.jar"
JOBS="br.ufc.scalding.jobs"
INPUT="/tweet-data/brasil-tweets-state.tsv"
OUTPUT="/output-brasil/"

for JOB in "CheckInPerStateJob"
do
    ${BIN} jar ${JAR} \
    com.twitter.scalding.Tool \
    ${JOBS}"."${JOB} \
    --hdfs \
    --input ${INPUT} \
    --output ${OUTPUT}${JOB}
done
