package objects //create package to allow importing required objects
import objects.main._ //import input from the main class
import objects.analysis._ //import all methods from analysis class

import java.sql._ //import java database drivers

/**
  * This is scala object to insert and retrieve data from database.
  * It inserts data based on analysis class and main class.
  * It selects data from last inserted result.
  * Confirmation message acknowledges that the data have been correctly inserted.
  *
  * Created by Kamil Stepien on 30/04/2016.
  * Version:  3.0
  * Contact:  s1405572@connect.glos.ac.uk
  */

object database { //create database object
//established database connection
  val url = "jdbc:mysql://localhost:3306/projectv3?autoReconnect=true&useSSL=false" //established database url
  val driver = "com.mysql.jdbc.Driver" //established JDBC driver
  val username = "root" //username for the database
  val password = "*****" //password for the database
  var connection: Connection = _ //all typed of connection

  try {
    Class.forName(driver) //pre-loading the driver class

    //Get a connection to database
    connection = DriverManager.getConnection(url, username, password)

    //Create a statement
    val statement = connection.createStatement

    //Execute insert SQL query
    val sql = "INSERT INTO tblanalysis " + "(input, wordCount, characterCountWords, characterCountInput, numPattern)" +
      "VALUES ('" + manInp + "', '" + countWords(manInp) + "', '" + characterCountWords(manInp) + "', '" +
        characterCountInput(manInp).mkString + "', '" + numberPattern(manInp) + "')"
    statement.executeUpdate(sql) //execute SQL query
    println("Analysis of the output have been successfully inserted to MySQL Workbench") //confirm that database have been inserted

    //Execute select SQL query
    val rs = statement.executeQuery("SELECT * FROM tblanalysis ORDER BY id DESC LIMIT 1")

    //Process select SQL query
    while (rs.next()) {
      val in = rs.getString("input")
      val wC = rs.getString("wordCount")
      val cCW = rs.getString("characterCountWords")
      val cCI = rs.getString("characterCountInput")
      val nP = rs.getString("numPattern")
      println("Input = %s, Word count = %s, Character count in words = %s, Character count in input = %s, Number patterns = %s".format
      (in, wC, cCW, cCI, nP))
    }
  }
  //Determine abnormal behaviour
  catch {
    case e: Exception => e.printStackTrace()
  }
  connection.close() //close the database connection
}
