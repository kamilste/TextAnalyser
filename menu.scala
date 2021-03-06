package objects //create package to allow importing required objects
import objects.main._ //import input from the main class
import objects.analysis._ //import all methods from analysis class

import scala.io.StdIn //import scala readInt

/**
  * This is menu object to allow menu to link all functionality of the system.
  * Menu import functions from analysis, main and database.
  * Menu displays list of options user is available to use.
  * Within the same function it requests a value which is used by functions to provide result.
  *
  * Created by Kamil Stepien on 30/04/2016.
  * Version:  3.0
  * Contact:  s1405572@connect.glos.ac.uk
*/

object menu { //create menu object

def readOption: Int = { //create a method to display menu option and request for a value
    println()
    println("""|Please select one of the following options:
               |  1 - Count words               |  4 - Put input into sentences |  7 - Find email patterns
               |  2 - Count characters in words |  5 - Separate characters      |  8 - Process all
               |  3 - Count characters in input |  6 - Find number patterns     |  9 - Exit""".stripMargin)
    StdIn.readInt() //request value from user
  }

  def men(option: Int): Boolean = { //create a method to match a users value
    actionMap.get(option) match { //get corresponding value from the actionMap
      case Some(a) => a() //match if value is existing
      case None => println("Option does not exist, try again") //if the does not exist display error message
        true //return true to keep options displayed
    }
  }

  val actionMap = Map[Int, () => Boolean](1 -> cWords, 2 -> cCharacters, 3 -> iCharacters, 4 -> sChunks, 5 -> sCharacters, 6 -> nPattern, 7 -> ePattern, 8 -> allCon, 9 -> exit) //map given value, to a method

  def cWords(): Boolean = { //create a method to display number of words
    println("Word count is: " + countWords(manInp)) //display number of words in user input
    true //return true to keep options displayed
  }

  def cCharacters(): Boolean = { //create method to display number of characters in words
    println("Character count in words is: " + characterCountWords(manInp)) //display number of characters in words
    true //return true to keep options displayed
  }

  def iCharacters(): Boolean = { //create method to print number of words in input
    println("Character count in input is: " + characterCountInput(manInp).mkString) //display number of characters in input
    true //return true to keep options displayed
  }

  def sChunks(): Boolean = { //create method to partition input into chunks
    println("Input into sentence chunks: ") //display message
    chunks(manInp) //partition user input by a method from analysis
    true //return true to keep options displayed
  }

  def sCharacters(): Boolean = { //create method to separate characters
    print("Characters separated: ") //display message
    separateCharacter(manInp) //separate user input by spaces using method from analysis
    true //return true to keep options displayed
  }

  def nPattern(): Boolean = { //create method to find number pattern
    println("Number patterns: " + numberPattern(manInp)) //display number patter from user input
    true //return true to keep options displayed
  }

  def ePattern(): Boolean = { //create method to find email pattern
    println("Email patterns: " + emailPattern(manInp)) //display email pattern from user input
    true //return true to keep options displayed
  }

  def allCon(): Boolean = { //create method to start all the threads
    threads.countThread(manInp) //start count thread
    threads.chunkThread(manInp) //start chunk thread
    threads.patternThread(manInp) //start pattern thread
    true //return true to keep options displayed
  }

  def exit(): Boolean = { //create method to exit program
    println("Thank you for using this program") //display message
    false //return false to close the program
  }
}
