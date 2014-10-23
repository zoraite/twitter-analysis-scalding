#!/bin/sh

BIN="hadoop"

JAR="../target/scala-2.10/twitter-analysis-scalding-0.0.1.jar"
JOBS="br.ufc.scalding.jobs"

INPUT="/tweet-data/brasil-tweets-state.tsv"
STOP="/stopwords.tsv"
OUTPUT="/output-brasil/"

for JOB in "BrasilTrendsJob"
do
    ${BIN} jar ${JAR} \
    com.twitter.scalding.Tool \
    ${JOBS}"."${JOB} \
    --local \
    --input ${INPUT} \
    --output ${OUTPUT}${JOB}

done
