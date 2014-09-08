#!/bin/sh

BIN="../../scalding/scripts/scald.rb"
JOBS="../src/main/scala/br/ufc/scalding/jobs/"

INPUT="../../brasil-tweets-state-sample.tsv.tsv"

for JOB in "CheckInPerStateJob"
do
    echo "Start Job: "$JOB
    $BIN "--local" $JOBS""$JOB".scala" "--input "$INPUT "--output" "../../"$JOB
    echo "End Job: "$JOB
done
