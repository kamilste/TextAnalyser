package objects //create package to allow importing required objects
import objects.menu._ //import all menu methods

import scala.io.StdIn //import scala readLine

/**
  * This is a main object of text analysis program.
  * This program is reading user input.
  * User input is accepted in form of manual typing into the console.
  * Analysis involve list of expandable options (listed in analysis class)
  *
  * This object is running until menu selection is closed.
  *
  * Created by Kamil Stepien on 30/04/2016.
  * Version: 3.0
  * Contact: s1405572@connect.glos.ac.uk
*/

object main { //create main object

println("-----------------------------------")
println("Welcome to Text Analyser, please provide input to see list of available analysis.")
println("-----------------------------------")

  val manInp: String = { //create a function to take user input
    StdIn.readLine("Please enter some text, and press enter: ").replaceAll("[']", "")//take user input and replace all "'" by space
  }
  //database.getClass //run database object

  def main(args: Array[String]) : Unit = { //create main function
    var opt = 0 //assign default option to 0

    do {
      try {
        opt = readOption //assign new variable to readOption
      }
      catch {
        case e: Exception => opt = 0 //catch unexpected answer (not included in the option menu)
      }
    }

    while (men(opt)) //cycle through menu option
  }
}