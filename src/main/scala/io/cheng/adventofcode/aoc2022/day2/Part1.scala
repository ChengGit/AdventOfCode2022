package io.cheng.adventofcode.aoc2022.day2

import scala.io.Source
import scala.util.matching.Regex
import OpponentInput._
import YourInput._

object Part1 extends App:

  val source = Source.fromResource("day2/part1.txt")
  val inputFormatRegex: Regex = raw"(\w)\s(\w)".r

  def lineToInputParser: String => GameInput =
    _ match
      case inputFormatRegex(oppo, you) => GameInput(oppo, you)

  val finalScore = source.getLines().foldLeft(0) {
    case (aggr, line) => aggr + lineToInputParser(line).getGameScore
  }

  println(s"[day2][part1] final score: $finalScore")

  source.close()

enum OpponentInput:
  case A // rock
  case B // paper
  case C // scissors

enum YourInput(val score: Int):
  case X extends YourInput(1) // rock
  case Y extends YourInput(2) // paper
  case Z extends YourInput(3) // scissors

case class GameInput(opponent: String, you: String):
  private def calculateGameScore =
    (OpponentInput.valueOf(opponent), YourInput.valueOf(you)) match
      case (A, X) => 3
      case (A, Y) => 6
      case (A, Z) => 0
      case (B, X) => 0
      case (B, Y) => 3
      case (B, Z) => 6
      case (C, X) => 6
      case (C, Y) => 0
      case (C, Z) => 3

  def getGameScore: Int =
    calculateGameScore + YourInput.valueOf(you).score