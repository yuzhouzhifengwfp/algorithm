package com.wfp.java.algorithm1

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks._

object BinarySearch {
  def main(args: Array[String]): Unit = {
//val arr:Array[Int]=(1 to 100 ).toArray
val arr:Array[Int]=Array(1,2,2,4,4,8)
    println(binarySearch2(arr, 0, 5, 2))
  }
  def binarySearch(arr:Array[Int],left:Int,right:Int,findValue:Int):Int={
    if(left>right){
        return -1
      }
    val mid=(left+right)/2;
    if(arr(mid)==findValue){
      mid
    }else if(arr(mid)<findValue){
      binarySearch(arr,mid+1,right,findValue)
    }else{
      binarySearch(arr,left,mid-1,findValue)
    }

  }


  def binarySearch2(arr: Array[Int], l: Int, r: Int,
                    findVal: Int): ArrayBuffer[Int] = {

    //找不到条件?
    if (l > r) {
      return ArrayBuffer()
    }

    val midIndex = (l + r) / 2

    val midVal = arr(midIndex)
    if (midVal > findVal) {
      //向左进行递归查找
      binarySearch2(arr, l, midIndex - 1, findVal)
    } else if (midVal < findVal) { //向右进行递归查找
      binarySearch2(arr, midIndex + 1, r, findVal)
    } else {
      println("midIndex=" + midIndex)
      //定义一个可变数组
      val resArr = ArrayBuffer[Int]()
      //向左边扫描
      var temp = midIndex - 1
      breakable {
        while (true) {
          if (temp < 0 || arr(temp) != findVal) {
            break()
          }
          if (arr(temp) == findVal) {
            resArr.append(temp)
          }
          temp -= 1
        }
      }
      //将中间这个索引加入
      resArr.append(midIndex)
      //向右边扫描
      temp = midIndex + 1
      breakable {
        while (true) {
          if (temp > arr.length - 1 || arr(temp) != findVal) {
            break()
          }
          if (arr(temp) == findVal) {
            resArr.append(temp)
          }
          temp += 1
        }
      }
      return resArr
    }
  }

  def aa={
    val conf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    val sc = new SparkContext(conf)
    sc.textFile("/input")
        .flatMap(_.split(" "))
        .map((_,1))
        .reduceByKey(_+_)
        .saveAsTextFile("/output")

    sc.stop()
  }

}


/*

 */
