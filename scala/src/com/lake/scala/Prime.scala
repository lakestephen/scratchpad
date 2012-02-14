package com.lake.scala

/**
 * SJL comment
 *
 * @author Stephen Lake
 *
 */
object Prime {

  def isPrime(n: Int) = (2 until n) forall (n % _ != 0)
}