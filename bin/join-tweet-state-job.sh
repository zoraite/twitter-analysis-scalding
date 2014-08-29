#!/bin/sh

BIN="~/foo/hadoop-1.0.3/bin/hadoop"
JAR="../target/scala-2.10/twitter-analysis-scalding-0.0.1.jar"
JOBS="br.ufc.scalding.jobs"
INPUT="/tweet-data/brasil-georef-tweets-world-cup-2014.tsv"
ESTADOS="/brasil-estados.tsv"
OUTPUT="/output-brasil/"

JOB="JoinTweetStateJob"

${BIN} jar ${JAR} \
com.twitter.scalding.Tool \
-D mapred.output.compress=false  \
-D mapred.child.java.opts=-Xmx2048m \
-D mapred.reduce.tasks=20 \
${JOBS}"."${JOB} \
--local \
--input ${INPUT} \
--estados ${ESTADOS}  \
--output ${OUTPUT}${JOB}


