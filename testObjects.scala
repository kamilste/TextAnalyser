package testing

import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import objects.analysis
import objects.threads


class testObjects extends FlatSpec with ScalaFutures {
"Number of words" should "return 0" in {
    val result = analysis.countWords("")
    assertResult(0) {result}
  }

  "Number of Words" should "return 6" in {
    val result = analysis.countWords("Running a test for word count")
    assertResult(6) {result}
  }

  "Number of characters in words" should "return 0" in {
    val result = analysis.characterCountWords("")
    assertResult(0) {result}
  }

  "Number of characters in words" should "return 12" in {
    val result = analysis.characterCountWords("Kamil Stepien")
    assertResult(12) {result}
  }

  "Number of characters in input" should "return 0" in {
    val result = analysis.characterCountInput("").size
    assertResult(0) {result}
  }

  "Number of characters in input" should "return 13" in {
    val result = analysis.characterCountInput("Kamil Stepien").mkString
    assertResult("13") {result}
  }

  "Sentence into chunks" should "return 2 sentences" in {
    val result = analysis.chunks("Kamil. Stepien.")
    assertResult() {result}
  }

  "Sentence into chunks" should "return 3 sentences" in {
    val result = analysis.chunks("s1405572. Kamil? Stepien!")
    assertResult() {result}
  }

  "The character separated" should "return separated characters" in {
    val result = analysis.separateCharacter("Kamil Stepien")
    assertResult() {result}
  }

  "The number pattern" should "return 1 number" in {
    val result = analysis.numberPattern("97978967")
    assert(result == "97978967")
  }

  "The number pattern" should "return multiple numbers by comers" in {
    val result = analysis.numberPattern("97,97,896,7")
    assert(result == "97,97,896,7")
  }

  "The number pattern" should "return multiple numbers" in {
    val result = analysis.numberPattern("9jds7sd97fds89fds67")
    assert(result == "9,7,97,89,67")
  }

  "The email pattern" should "return 1 email" in {
    val result = analysis.emailPattern("kamilstepien@hotmail.co.uk")
    assert(result == "kamilstepien@hotmail.co.uk")
  }

  "The email pattern" should "return 3 emails" in {
    val result = analysis.emailPattern("kamilstepien@hotmail.co.uk, uzierek@gmail.com, kamilste@hotmail.com")
    assert(result == "kamilstepien@hotmail.co.uk,uzierek@gmail.com,kamilste@hotmail.com")
  }

  "The email pattern" should "return 1 hidden email" in {
    val result = analysis.emailPattern("jsf dah nlhv  gnr 87436.ffdg.dum kamilstepien@hotmail.co.uk rg ivi ysuj.df85 74tuv reyruvj")
    assert(result == "kamilstepien@hotmail.co.uk")
  }

  "The count thread" should "return count analysis" in {
    val f = threads.countThread("s1405572. Kamil Stepien. s1405572@connect.glos.ac.uk")
    whenReady(f) { result => assertResult(result) {}}
  }

  "The chunk thread" should "return chunk analysis" in {
    val f = threads.chunkThread("s1405572. Kamil Stepien. s1405572@connect.glos.ac.uk")
    whenReady(f) { result => assertResult(result) {}}
  }

  "The pattern thread" should "return patern analysis" in {
    val f = threads.patternThread("s1405572. Kamil Stepien. s1405572@connect.glos.ac.uk")
    whenReady(f) { result => assertResult(result) {} }
  }

  "The thread test" should "return 2 threads" in {
    val f = threads.patternThread("s1405572. Kamil Stepien. s1405572@connect.glos.ac.uk")
    val p = threads.chunkThread("s1405572. Kamil Stepien. s1405572@connect.glos.ac.uk")
    whenReady(f) { result => assertResult(result) {} }
    whenReady(p) { result => assertResult(result) {} }
  }

   "The thread test" should "return all threads" in {
    val f = threads.patternThread("s1405572. Kamil Stepien. s1405572@connect.glos.ac.uk")
    val p = threads.chunkThread("s1405572. Kamil Stepien. s1405572@connect.glos.ac.uk")
    val c = threads.patternThread("s1405572. Kamil Stepien. s1405572@connect.glos.ac.uk")
    whenReady(f) { result => assertResult(result) {} }
    whenReady(p) { result => assertResult(result) {} }
    whenReady(c) { result => assertResult(result) {} }
  }
}