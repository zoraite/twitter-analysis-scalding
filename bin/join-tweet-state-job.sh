
BIN="../../scalding/scripts/scald.rb"
JOBS="../src/main/scala/br/ufc/scalding/jobs/"

INPUT="/tweet-data/brasil-georef-tweets-world-cup-2014.tsv"
OUTPUT="/output-brasil"

ESTADOS="/brasil-estados.tsv"

JOB="JoinTweetStateJob"

$BIN "--hd" $JOBS""$JOB".scala" "--input "$INPUT "--estados "$ESTADOS "--output" $JOB

