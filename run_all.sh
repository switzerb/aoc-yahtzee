#!/usr/bin/env bash
for i in `find . -name run.sh | sort`; do
 $i
done