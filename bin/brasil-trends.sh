#!/bin/sh

BIN="hadoop"

JAR="../target/scala-2.10/twitter-analysis-scalding-0.0.1.jar"
JOBS="br.ufc.scalding.jobs"

INPUT="/Users/igobrilhante/Desktop/debate/debate-tweets-estado.tsv"
STOP="../../data/stopwords.tsv"
OUTPUT="/Users/igobrilhante/Desktop/debate/"
TOP=20

for JOB in "BrasilTrendsJob" "StateTrendsJob"
do
    ${BIN} jar ${JAR} \
    com.twitter.scalding.Tool \
    -D mapred.reduce.tasks=20 \
    -D mapred.min.split.size=2000000000 \
    ${JOBS}"."${JOB} \
    --hdfs \
    --input ${INPUT} \
    --stop ${STOP} \
    --k ${TOP} \
    --output ${OUTPUT}${JOB}

done
