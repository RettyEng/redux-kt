#!/bin/bash

if type mvn 1>/dev/null 2>&1; then
    cd $(dirname $0)/.. && mvn install
else
    echo 'Firstly, you need to install maven to install snapshot into local cache.'
fi
