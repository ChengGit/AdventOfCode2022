package io.cheng.adventofcode.aoc2022.day3

import scala.io.Source

object Part1 extends App {

  val source = Source.fromResource("day3/part1.txt")

  val totalPriorities = source.getLines().foldLeft(0) {
    case (aggr, line) =>
      val (firstHalf, secondHalf) = line.splitAt(line.length / 2)
      // intersect of 2 sets are the common elements appearing in both sets
      aggr + firstHalf.toSet.intersect(secondHalf.toSet)
        .map(letterToPriorityScore)
        .sum
  }

  println(s"[day2][part1] total priorities: $totalPriorities")

  def letterToPriorityScore: Char => Int =
    char =>
      if char.isLower then
        char - 'a' + 1
      else
        char - 'A' + 27

}
