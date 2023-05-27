package com.chenxii.jinghong.hadoop

import org.apache.spark.sql.SparkSession

@SerialVersionUID(11111L)
object SparkHiveTest extends Serializable {

  def main(args: Array[String]): Unit = {
    val sc = SparkSession.builder()
      .master("spark://192.168.31.66:7077")
      .appName("SparkHiveTest")
      .config("spark.sql.warehouse.dir", "hdfs://192.168.31.66:9000/opt/hive/hive-3.1.2/warehouse")
      .enableHiveSupport()
      .getOrCreate()

    println("连接成功")

    val sql = "select * from jinghong.rate"
    val result = sc.sql(sql)
    result.show()
    sc.stop()
  }

}
