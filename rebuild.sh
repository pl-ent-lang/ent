#!/bin/bash

#export JAVA_HOME=/home/acanino/Src/jdk1.8.0_60

cwd=$(pwd)
cd /Users/acanino/Research/polyglot-personal
ant jar
cd $cwd

cp /Users/acanino/Research/polyglot-personal/lib/polyglot.jar ./lib
ant clean
ant
