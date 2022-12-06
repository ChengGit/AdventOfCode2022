package io.cheng.adventofcode.aoc2022.day2

import scala.io.Source
import scala.util.matching.Regex
import Part1.ElfInput._
import Part1.YourInput._

object Part1 extends App:
// some helper data structures
  enum ElfInput:
    case A // rock
    case B // paper
    case C // scissors

    def loseTo: YourInput = this match {
      case A => Y
      case B => Z
      case C => X
    }

  enum YourInput(val score: Int):
    case X extends YourInput(1) // rock
    case Y extends YourInput(2) // paper
    case Z extends YourInput(3) // scissors

    def loseTo: ElfInput = this match {
      case X => B
      case Y => C
      case Z => A
    }

  case class GameInput(elf: String, you: String):
    private def calculateGameScore =
      val elfInput = ElfInput.valueOf(elf)
      val yourInput = YourInput.valueOf(you)

      if (elfInput.loseTo == yourInput) 6
      else if (yourInput.loseTo == elfInput) 0
      else 3

    def getGameScore: Int =
      calculateGameScore + YourInput.valueOf(you).score


//  logic
  val source = Source.fromResource("day2/part1.txt")
  val inputFormatRegex: Regex = raw"(\w)\s(\w)".r
// use `regular expression` and `capture group` to extract elf input and your input from each line
  def lineToInputParser: String => GameInput =
    _ match
      case inputFormatRegex(oppo, you) => GameInput(oppo, you)

  val finalScore = source.getLines().foldLeft(0) {
    case (aggr, line) => aggr + lineToInputParser(line).getGameScore
  }

  println(s"[day2][part1] final score: $finalScore")

  source.close()