
BIN="../../scalding/scripts/scald.rb"
JOBS="../twitter-analysis-scalding/src/main/scala/br/ufc/scalding/jobs/"

INPUT="../../brasil-georef-tweets-world-cup-2014.tsv"

for JOB in "UniqueUsersJob" "UniqueTweetsJob" "TweetPerDayJob" "UsersPerDayJob"
do
    echo "Start Job: "$JOB
    $BIN "--local" $JOBS""$JOB".scala" "--input "$INPUT "--output" "../../"$JOB
    echo "End Job: "$JOB
done
