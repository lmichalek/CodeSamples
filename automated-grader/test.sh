#!/bin/bash

if [ ! -d MASTER ]
then
    echo "You are missing the MASTER folder or you are running this from the wrong folder."
    exit
fi

if [ $# -lt 1 ]
then
    echo "Which ID would you like to copy into MASTER folder?  "
    read id
else
    id=$1
fi

file="$id.1"

echo "Where its copying from: "
copy=`find $file | grep ListPractice.java`
echo $copy

cp "$copy" MASTER
javac MASTER/*.java
cd MASTER

if [ $# -lt 2 ]
then
	java HW3Tester
else
	java PrintMethod "$2"
fi

rm ListPractice.*
cd ..
