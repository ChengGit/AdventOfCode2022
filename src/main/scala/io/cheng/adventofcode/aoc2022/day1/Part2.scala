package io.cheng.adventofcode.aoc2022.day1

import scala.io.Source
import scala.util.control.NonFatal

object Part2 extends App:
  val source = Source.fromResource("day1/part1.txt")

  val seenMaxCalories = source.getLines().foldLeft((Seq[Int](), 0)) {
    case ((aggr, tempAggr), "") => (aggr.appended(tempAggr),0)
    case ((aggr, tempAggr), str) => (aggr, tempAggr + str.toInt)
  }._1.sorted.reverse.take(3)

  println(s"[day1][part1] seenMaxCalories: ${seenMaxCalories.sum}")

  source.close()


