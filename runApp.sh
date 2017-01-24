###
# Copyright 2016 MapR Technologies, Inc.
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
#  limitations under the License.
#
# Author: Dong Meng
# dmeng@mapr.com
###


#param="$1"
#if [ ! -n "$pparam" ] ; then
#	echo "ERROR: missing input param."
#	echo "USAGE: $0 input_param"
#	exit 1
#fi
DIR="$( cd "$( dirname "${BATH_SOURCE[0]}" )" && pwd )"
#export MASTER="yarn-client"
spark-submit --master yarn-client --num-executors 4 --driver-memory 4g --executor-memory 6g --executor-cores 4 \
    --jars /home/dmeng/hbase_2.10-0.1.jar \
    --conf spark.akka.timeout=200 \
    --conf spark.core.connection.ack.wait.timeout=180 \
    --conf spark.akka.frameSize=256 \
    --class "com.maprps.uhg.dfhbase.TestHbase" ${DIR}/target/scala-2.10/dfhbase_2.10-0.1.jar
