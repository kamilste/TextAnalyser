package objects //create package to allow importing required objects

/**
  * This is scala object to allow analyse given input.
  * Based on this input perform analysis.
  * Analysis involve:
  *   - Word count
  *   - Character count on words
  *   - Character count on input
  *   - Split input into sentences
  *   - Separate each character
  *   - Find number pattern
  *   - Find email pattern
  * Results from analysis methods are used by other objects to display results.
  *
  * Created by Kamil Stepien on 30/04/2016.
  * Version:  3.0
  * Contact:  s1405572@connect.glos.ac.uk
  */

object analysis { //create analysis object
  def countWords(x: String): Int = { //create method to count words
    if (x.length == 0) {0} else { //if input is empty set word count to 0
    x.split("\\s+").length} //split input by words and get its size
  }

  def characterCountWords(x: String) = { //create method to count characters in words
    //replace all symbols and numbers by  empty space and get length
    x.replaceAll("[-!$%^&*()_+|~=`{} \\[\\]:\";'<>?,.\\\\/]", "").replaceAll("[0-9]", "").length
  }

  def characterCountInput(x: String) = { //create method to count all characters in input
    // matched input characters + get input from the map
    x.foldLeft(Map.empty[String, Int]) {(c, w) => c + (x -> (c.getOrElse(x, 0) + 1))}.get(x)
  }

  def chunks(x: String) = { //create method to split sentences
    x.split("\\. |\\? |\\! ").foreach(println) //split by "." or "?" or "!"
  }

  def separateCharacter(x: String): Unit = { //create method to separate characters
    val y = x.iterator //iterate trough given parameter
    while (y.hasNext) { //while it has next
      print(y.next() + " ") //print next and separate it by ","
    }
  }

  def numberPattern(x: String) = { //create a method to find number patterns
    "[0-9]+".r.findAllIn(x).mkString(",") //find all numeric values and separate it by ","
  }

  def emailPattern(x: String) = { //create a method to find email pattern
    """(\w+)@([\w\.]+)""".r.findAllIn(x).mkString(",") //find all email formats and separate it by ","
  }
}
