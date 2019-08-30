#!/bin/bash
dir=`dirname "$0"`;
java="java"
initHeap="2G"
maxHeap="10G"
stack="1G"
jar="dist/recall.jar"
$java -Xms$initHeap -Xss$stack -Xmx$maxHeap -jar $dir/$jar $@
