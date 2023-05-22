package com.chenxii.jinghong.hadoop

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object NetworkWordCount {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
      .setAppName("NetworkWordCount")
      .setMaster("local[*]")

    val sc = new StreamingContext(sparkConf, Seconds(5))
    val lines = sc.socketTextStream("192.168.31.135", 9999)
    lines.flatMap(_.split(" ")).map(x => (x, 1)).reduceByKey(_ + _).print()

    sc.start()
    sc.awaitTermination()
  }

}
