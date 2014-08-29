#!/bin/sh

BIN="~/foo/hadoop-1.0.3/bin/hadoop"
JAR="../target/scala-2.10/twitter-analysis-scalding-0.0.1.jar"
JOBS="br.ufc.scalding.jobs"
INPUT="/tweet-data/rio-georef-tweets.tsv"
OUTPUT="/output-brasil/"

for JOB in "UniqueUsersJob" "UniqueTweetsJob" "TweetPerDayJob" "UsersPerDayJob"
do
    ${BIN} jar ${JAR} \
    com.twitter.scalding.Tool \
    -D mapred.output.compress=false  \
    -D mapred.child.java.opts=-Xmx2048m \
    -D mapred.reduce.tasks=20 \
    ${JOBS}"."${JOB} \
    --local \
    --input ${INPUT} \
    --output ${OUTPUT}${JOB}
done
