
BIN="../scalding/scripts/scald.rb"
JOBS="../twitter-scalding/src/main/scala/br/ufc/scalding/jobs/"

INPUT="../data/small-sample.tsv"
ESTADOS="../data/brasil-estados.tsv"

for JOB in "ODMatrixJob"
do
    $BIN "--local" $JOBS""$JOB".scala" "--input "$INPUT "--estados "$ESTADOS "--output" "./"$JOB
done
