
BIN="../../scalding/scripts/scald.rb"
JOBS="../twitter-analysis-scalding/src/main/scala/br/ufc/scalding/jobs/"

INPUT="/tweet-data/brasil-georef-tweets-world-cup-2014.tsv"
OUTPUT="/output-brasil"
#INPUT="/tweet-data/rio-georef-tweets.tsv"
#OUTPUT="/output-rio"

for JOB in "UniqueUsersJob" "UniqueTweetsJob" "TweetPerDayJob" "UsersPerDayJob"
do
    echo "Start Job: "$JOB
    $BIN "--hdfs" $JOBS""$JOB".scala" "--input "$INPUT "--output" $OUTPUT"/"$JOB
    echo "End Job: "$JOB
done
