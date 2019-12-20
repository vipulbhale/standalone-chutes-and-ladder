#!/bin/bash
cmd=$0
args="$@"
bin_home=$(dirname ${cmd})
if [[ ${bin_home} == '.' ]]; then
  bin_home=$(pwd)
fi
app_home=$(dirname ${bin_home})
echo "Running the command from :: "${app_home}
java -Xmx256m -classpath ${app_home}/lib -jar ${app_home}/standalone-chutesladders-*.jar ${args}