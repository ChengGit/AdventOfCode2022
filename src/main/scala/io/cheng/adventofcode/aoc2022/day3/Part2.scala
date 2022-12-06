package io.cheng.adventofcode.aoc2022.day3

import scala.io.Source

object Part2 extends App {
  val source = Source.fromResource("day3/part1.txt")

  val totalPriorities = source.getLines().grouped(3).foldLeft(0) {
    case (aggr, listOfThreeLines) =>
      aggr +
        listOfThreeLines.map(_.toSet).reduce(_ intersect _).map(letterToPriorityScore).sum
  }

  println(s"[day2][part2] total priorities: $totalPriorities")

  def letterToPriorityScore: Char => Int =
    char =>
      if char.isLower then
        char - 'a' + 1
      else
        char - 'A' + 27

}
