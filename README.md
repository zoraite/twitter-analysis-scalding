twitter-analysis-scalding
=========================

This project has some map-reduce Jobs to deal with tweets, specially a huge number of them.
It relies on Scalding framework for Scala language to execute the jobs on a hadoop environment.

- bin: executables to run the jobs
- src: source codes of the jobs

Since it runs on hadoop, the dataset must be added into hdfs. 

Generate the "fat jar"
=======================
git clone twitter-analysis-scalding

sbt assembly

After that, the jar will be created on target/scala-2.10/twitter-analysis-scalding-*.jar. Scripts *.sh in bin/ can be now executed. Recalling you may have to set up the hadoop on the scripts -- BIN="hadoop"

