#!/bin/sh

BIN="hadoop"
#BIN="././../../scalding/scripts/scald.rb"
JAR="../target/scala-2.10/twitter-analysis-scalding-0.0.1.jar"
JOBS="br.ufc.scalding.jobs"
#JOBS="././../src/main/scala/br/ufc/scalding/jobs/"
INPUT="../../data/brasil-tweets-state-sample.tsv"
ESTADOS="../../data/brasil-estados.tsv"
OUTPUT="../../results/"

for JOB in "TweetsPerStateJob"
do
    ${BIN} jar ${JAR} \
    com.twitter.scalding.Tool \
    ${JOBS}"."${JOB} \
    --local \
    --input ${INPUT} \
    --output ${OUTPUT}${JOB}

#    ${BIN} "--local" ${JOBS}""${JOB}".scala" "--input "${INPUT} "--output" ${OUTPUT}"/"${JOB}
done
