#!/usr/bin/env bash

if ! hash ent 2>/dev/null; then
  echo "ent not found in path"
  exit 1
fi

if ! hash entc 2>/dev/null; then
  echo "entc not found in path"
  exit 1
fi

fails=0
passed=0

for d in */ ; do
  cd ./$d
  result=$(./test.bash 2> /dev/null)
  status=$?
  cd ..
  if [[ $status != 0 ]]; then
    printf "FAIL %10s\n" $d
    fails=$(($fails+1))
  else
    printf "OK %10s\n" $d
    passed=$(($passed+1))
  fi
done

echo "run testing completed with $passed passes and $fails failures"
if [[ $fails != 0 ]]; then
  exit 1
else
  exit 0
fi
