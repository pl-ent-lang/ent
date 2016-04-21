#!/usr/bin/env bash

# Copyright 2015 The Zebu Authors. All rights reserved.

if [ ! -f run.rb ]; then
  echo "test.bash must be run from $ENTROOT/tests" 1>&2
  exit 1
fi

if ! hash ent 2>/dev/null; then
  echo "ent not found in path"
  exit 1
fi

if ! hash entc 2>/dev/null; then
  echo "entc not found in path"
  exit 1
fi

if ! hash eth 2>/dev/null; then
  echo "eth not found in path"
  exit 1
fi

echo "running eth harness"
eth pthScript

echo "running run harness"

fails=0
passed=0
for f in $(find . -path ./build -prune -o -type d -regex '.*\(pass\|fail\).*' -print); do
  result=$(./run.rb $f)
  status=$?
  if [[ $status != 0 ]]; then
    printf "FAIL %10s\n" $f
    fails=$(($fails+1))
  else
    printf "OK %10s\n" $f
    passed=$(($passed+1))
  fi
done

echo "run testing completed with $passed passes and $fails failures"
if [[ $fails != 0 ]]; then
  exit 1
else
  exit 0
fi
