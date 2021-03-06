package objects //create package to allow importing required objects

import scala.concurrent.Future //import scala future thread
import scala.concurrent.ExecutionContext.Implicits.global //import

/**
  * This is scala object to allow multi-threading.
  * There is total of 3 threads
  *   - Count thread to take perform all calculations on the input.
  *   - Chunk thread to deal with splitting sentences.
  *   - Pattern thread to find patterns in the input
  * Results from threads are initialised by menu object to display output to the user concurrently.
  *
  * Created by Kamil Stepien on 30/04/2016.
  * Version:  3.0
  * Contact:  s1405572@connect.glos.ac.uk
*/

object threads { //create thread objects
def countThread(x: String) = Future { //create count thread
    if (x.length == 0) {println("Word count is: 0")} else {println("Word count is: " + x.split("\\s+").length)} //if input is empty set word count to 0 else //display number of words
    println("Character count in words is: " + x.replaceAll("[-!$%^&*()_+|~=`{} \\[\\]:\";'<>?,.\\\\/]", "").replaceAll("[0-9]", "").length) //display character count in words by replacing all symbols and numbers by empty space, and getting length
    println("Character count in input is: " + x.foldLeft(Map.empty[String, Int]) {(c, w) => c + (x -> (c.getOrElse(x, 0) + 1))}.get(x).mkString) //display character count by matching given input characters + getting value from the map
  }

  def chunkThread(x: String) = Future { //crete chunk thread
    println("Input into sentence chunks: " + x.split("\\. |\\? |\\! ").foreach(println)) //display sentences separated by "." "?" "!"
    val separateCharacter = x.iterator //iterate trough given parameter
    println("Characters separated: ") //display message
    while (separateCharacter.hasNext) { //while it has next
      print(separateCharacter.next() + " ") //print next and separate it by ","
    }
  }

  def patternThread(x: String) = Future { //create pattern thread
    println("Number patterns: " + "[0-9]+".r.findAllIn(x).mkString(",")) //find all numeric values, separate it by "," and display it
    println("Email patterns: "+ """(\w+)@([\w\.]+)""".r.findAllIn(x).mkString(",")) //find all email formats, separate it by "," and display it
  }
}
