package io.cheng.adventofcode.aoc2022.day2

import io.cheng.adventofcode.aoc2022.day2.Part2.ElfInput.*
import io.cheng.adventofcode.aoc2022.day2.Part2.YourInput.*
import io.cheng.adventofcode.aoc2022.day2.Part2.GameResult.*

import scala.io.Source
import scala.util.matching.Regex

object Part2 extends App:
// some helper data structures
  enum ElfInput:
    case A // rock
    case B // paper
    case C // scissors

    def loseTo: YourInput = this match
      case A => Paper
      case B => Scissors
      case C => Rock


    def beat: YourInput = this match
      case A => Scissors
      case B => Rock
      case C => Paper


    def drawTo: YourInput = this match
      case A => Rock
      case B => Paper
      case C => Scissors


  enum GameResult(val score: Int):
    case X extends GameResult(0) // lose
    case Y extends GameResult(3) // draw
    case Z extends GameResult(6) // win

  enum YourInput(val score: Int):
    case Rock extends YourInput(1) // rock
    case Paper extends YourInput(2) // paper
    case Scissors extends YourInput(3) // scissors

  case class GameInput(elf: String, result: String):
    def getGameScore: Int =
      val gameResult = GameResult.valueOf(result)
      val elfInput = ElfInput.valueOf(elf)
      gameResult match
        case X => elfInput.beat.score + X.score  // X mean elf win
        case Y => elfInput.drawTo.score + Y.score // Y means Elf draw
        case Z => elfInput.loseTo.score + Z.score // Z means Elf lose

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