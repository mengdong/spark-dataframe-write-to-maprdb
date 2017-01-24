package com.maprps.dfhbase

import org.apache.spark.sql._
import org.apache.spark.sql.execution.datasources.hbase._

import org.apache.log4j.{Level, Logger}

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.hive.HiveContext

object TestHbase extends Serializable{

    def main(args: Array[String]) = {
        Logger.getLogger("org").setLevel(Level.WARN)
        Logger.getLogger("akka").setLevel(Level.WARN)

        val conf = new SparkConf().setAppName(this.getClass.getSimpleName)
        //conf.set("spark.serializer","org.apache.spark.serializer.KryoSerializer")
        val sc = new SparkContext(conf)

        run(sc)
        //sc.stop()
    }

    def run(sc: SparkContext): Unit = {	    
        val sqlContext = new HiveContext(sc)
        import sqlContext.implicits._
        
        val df = sqlContext.sql("select concat_ws('-', member_key, hc_diag_code1) as key, hc_diag_code1, scr, rnk from 1063_dmr.mbr_pred")
        val cat = s"""{
            |"table":{"namespace":"default", "name":"/datalake/uhc/ei/pi_ara/users/dmeng/maprdb_tables/test1/test-01"},
            |"rowkey":"key",
            |"columns":{
              |"key":{"cf":"rowkey", "col":"key", "type":"string"},
              |"hc_diag_code1":{"cf":"f", "col":"hc_diag_code1", "type":"string"},
              |"scr":{"cf":"f", "col":"scr", "type":"double"},
              |"rnk":{"cf":"f", "col":"rnk", "type":"int"}
            |}
            |}""".stripMargin
        
        val startTime=System.nanoTime()
        df.write.options(
            Map(HBaseTableCatalog.tableCatalog -> cat))
            .format("org.apache.spark.sql.execution.datasources.hbase")
            .save()   
        val endTime = System.nanoTime()
        println("Time elapsed: " + (endTime-startTime)/1000000000 + " seconds")
    }
}

        
