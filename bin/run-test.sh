#!/bin/sh

BIN="hadoop"
JAR="../target/scala-2.10/twitter-analysis-scalding-0.0.1.jar"
JOBS="br.ufc.scalding.jobs"
INPUT="../../data/small-sample.tsv"
ESTADOS="../../data/brasil-estados.tsv"
OUTPUT="../../results/"

for JOB in "JoinTweetStateJob"
do
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
#    ${BIN} "--local" ${JOBS}""${JOB}".scala" "--input "${INPUT} "--estados "${ESTADOS} "--output" ${OUTPUT}"/"${JOB}
done
