/*
***
* Copyright 2016 MapR Technologies, Inc.
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
*  limitations under the License.
*
* Author: Dong Meng
* dmeng@mapr.com
***
*/

name := "dfhbase" 

organization := "com.maprps" 

version := "0.1"

scalaVersion := "2.10.5"

resolvers ++= Seq(
  "repo" at "http://repository.mapr.com/maven"
)

val sparkVersion = "1.6.1"
val hbaseVersion = "1.1.1-mapr-1602"
val hadoopVersion = "2.7.0-mapr-1602"

libraryDependencies ++= Seq(
  // logging
  "org.apache.logging.log4j" % "log4j-api" % "2.5",
  "org.apache.logging.log4j" % "log4j-core" % "2.5",
  // spark
  "org.apache.spark" %% "spark-core" % "1.6.1" % "provided",
  "org.apache.spark" %% "spark-sql" % "1.6.1" % "provided",
  "org.apache.spark" %% "spark-hive" % "1.6.1" % "provided",
  "com.databricks" % "spark-csv_2.10" % "1.5.0",
  
  // "com.databricks" %% "spark-csv" % "1.4.0",
  "org.apache.hbase" % "hbase-common" % hbaseVersion % "provided",
  "org.apache.hbase" % "hbase-client" % hbaseVersion % "provided",
  "org.apache.hbase" % "hbase-server" % hbaseVersion % "provided"
)

// resolvers += "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven/"

