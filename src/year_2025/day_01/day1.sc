
import java.io.File
import scala.annotation.tailrec
import scala.io.Source


val filename = "C:\\Users\\reinh\\IdeaProjects\\AdventOfCode_2019_Java\\src\\year_2025\\day_01\\day_01_input.txt"
val small_filename = "C:\\Users\\reinh\\IdeaProjects\\AdventOfCode_2019_Java\\src\\year_2025\\day_01\\day_01_small_input.txt"

def parse_filename(filename: String): List[String] =
  val file: File = File(filename)
  val source = Source.fromFile(file)
  source.getLines().toList

val small_lines = parse_filename(small_filename)
val lines = parse_filename(filename)

val NUM_POSITIONS = 100

class Instruction(val dir : Char, val amt : Int):

 def this(input: String) =
    this(input.head, input.tail.toInt)

 def apply(initialPosition: Int): Int =
   if dir == 'L' then (initialPosition - amt + NUM_POSITIONS) % NUM_POSITIONS else (initialPosition + amt) % NUM_POSITIONS

 def endsAtZero(initialPosition: Int): Boolean = apply(initialPosition) == 0

 def timesClickingZero(initialPosition: Int): Int =
   val fullRotations: Int = Math.floor(amt / NUM_POSITIONS).toInt
   val remainder: Int = amt % NUM_POSITIONS

   fullRotations + (
     if dir == 'L' then
       if (remainder >= initialPosition) && initialPosition != 0 then 1 else 0
     else
       if(remainder + initialPosition >= NUM_POSITIONS) then 1 else 0
     )
  
  
 override def toString = f"(dir=${dir}, amt=${amt})"

class LeftInstruction(override val amt: Int) extends Instruction('L', amt):
  override def apply(initialPosition: Int): Int = (initialPosition - amt + NUM_POSITIONS) % NUM_POSITIONS



assert(Instruction('R', 1000).timesClickingZero(50) == 10)
assert(Instruction('L', 68).timesClickingZero(50) == 1)


def toInstructionList(lines: List[String]) : List[Instruction] = lines.map(Instruction(_))

val small_instructions = toInstructionList(small_lines)
val instructions = toInstructionList(lines)

@tailrec
def applyLines(dialPosition: Int, instructions: List[Instruction], timesAtZero : Int): Int = instructions match
  case i :: xs =>
    val newDialPosition = i.apply(dialPosition)
    applyLines(newDialPosition, xs, if newDialPosition == 0 then timesAtZero + 1 else timesAtZero)
  case _ => timesAtZero


@tailrec
def applyLines2(dialPosition: Int, instructions: List[Instruction], timesAtZero: Int): Int = 
  //println(f"d=$dialPosition, T=$timesAtZero")
  assert(dialPosition >= 0 && dialPosition < NUM_POSITIONS)
  instructions match
  case i :: xs => i.amt match
      case 0 => applyLines2(dialPosition, xs, timesAtZero)
      case _ => 
        val newDialPosition = (dialPosition + (if i.dir == 'R' then 1 else -1)) % NUM_POSITIONS 
        val actualNewDialPosition = if newDialPosition < 0 then newDialPosition + NUM_POSITIONS else newDialPosition
        applyLines2(actualNewDialPosition, Instruction(i.dir, i.amt - 1) :: xs, timesAtZero + (if actualNewDialPosition == 0 then 1 else 0))
  case _ => timesAtZero



assert(applyLines(50, small_instructions, 0) == 3)
applyLines2(50, small_instructions, 0)
assert(applyLines(50, instructions, 0) == 989)
applyLines2(50, instructions, 0)


