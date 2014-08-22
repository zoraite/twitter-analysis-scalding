
BIN="../../scalding/scripts/scald.rb"
JOBS="../twitter-analysis-scalding/src/main/scala/br/ufc/scalding/jobs/"

INPUT="/tweet-data/large-sample.tsv"

for JOB in "UniqueUsersJob" "UniqueTweetsJob" "TweetPerDayJob" "UsersPerDayJob"
do
    $BIN "--hdfs" $JOBS""$JOB".scala" "--input "$INPUT "--output" $JOB
done
