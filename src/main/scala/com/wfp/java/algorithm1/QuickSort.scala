package com.wfp.java.algorithm1

object QuickSort {
  def main(args: Array[String]): Unit = {
    val list = List(55, 2, 6, 9, 324, 21, 1, 3546, 8, 3, 23, 43, 5, 324, 12,12, 56, 2, 24, 53)
    println(quickSort(list))
  }

  def quickSort(list: List[Int]): List[Int] = {
    list match {
      case Nil => Nil
      case List() => List()
      case head :: tail =>
        val (left, right) = tail.partition(_ < head)
        quickSort(left) ::: head :: quickSort(right)
    }
  }
}
