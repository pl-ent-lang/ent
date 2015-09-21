#!/bin/bash

cwd=$(pwd)
cd /Users/acanino/Research/polyglot-personal
ant jar
cd $cwd

cp /Users/acanino/Research/polyglot-personal/lib/polyglot.jar ./lib
ant clean
ant
