package com.wfp.java.algorithm1



object Merge {
  def main(args: Array[String]): Unit = {
    val list = List(55, 2, 6, 9, 324, 21, 1, 3546, 8, 3, 23, 43, 5, 324, 12,12, 56, 2, 24, 53)
   println(msort[Int](_<_)(list))
  }
  def merge(left: List[Int], right: List[Int]): List[Int] = (left, right) match {
    case (Nil, _) => right
    case (_, Nil) => left
    case (x :: xTail, y :: yTail) =>
      if (x <= y) x :: merge(xTail, right)
      else y :: merge(left, yTail)
  }

  def msort[T](less: (T, T) => Boolean) (xs: List[T]): List[T] = {
    //定义merge方法。对于两个已经有序的集合进行merge，结果也有序。
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match {
        case (Nil, _) => ys
        case (_, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (less(x, y)) x :: merge(xs1, ys) else y :: merge(xs, ys1)
      }

    //排序逻辑
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      //msort对ys进行排序，msort对zs进行排序，两个有序的集合进行merge，最终结果也有序。
      merge(msort(less)(ys), msort(less)(zs))
    }
  }
}
