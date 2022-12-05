package io.cheng.adventofcode.aoc2022.day1

import scala.io.Source
import scala.util.control.NonFatal

object Part1 extends App:

  def using[T](acquire: => T)(release: T => Unit)(use: T => Unit) =
    try
      use(acquire)
    catch
      case NonFatal(error) =>
        println(s"error: $error")
    finally
      release(acquire)

  val source = Source.fromResource("day1/part1.txt")


  val seenMaxCalories = source.getLines().foldLeft((-1, 0)) {
    case ((seenMax, tempAggr), "") => (math.max(seenMax, tempAggr),0)
    case ((seenMax, tempAggr), str) => (seenMax, tempAggr + str.toInt)
  }

  println(s"[day1][part1] seenMaxCalories: ${seenMaxCalories._1}")

  source.close()


