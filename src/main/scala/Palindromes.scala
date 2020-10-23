import Palindromes._
import scala.collection.mutable.ArrayBuffer
import java.io.File
import java.io.PrintWriter






/*
 * CS3210 - Principles of Programming Languages - Fall 2020
 * Instructor: Thyago Mota
 * Description: Prg02 - PalindromesSearch
 * Student: Larsen Close and Stuart Griffin
 *
 * TODO
 * 1. Speed it up
 */




class Palindromes (private var n: Byte, private var m: Byte, informed: Boolean) {

  def results() = ArrayBuffer.fill(n)(0.toByte)
  val pw = new PrintWriter(new File("tmp.txt" ))

  if (informed){
    println("Generating palindromic sequences...\n\n\n")
    pw.write(find_combinations_sum_n(1,n,results, 0).toString())
    pw.close
    println("\n\nDone!\n\n")
    if(DEBUG){printStatistics()}
  }
  else {
    find_combinations_sum_n(1,n,results, 0)
    printStatistics()
    
     
  }

  def printStatistics()  {
    if (DEBUG)println("\nTotal number of palindromic sequences found: " + count_palindromes)
    println("\nNumber of palindromic sequences found which contain " + m +  " is: " + count_matching_m)
    printf("\nDuration: %.5fs\n", (System.nanoTime() - t) / scala.math.pow(10, 9))
    
  }


  def find_combinations_sum_n (i: Byte, n: Byte, results: ArrayBuffer[Byte], index: Byte) {
    // base case result (take) index contains a combinatorial summation equal to n
		if (n == 0) { permute_and_palendrome(results.take(index)) } //;print(results.take(index))
    var x: Byte = i
    for (x <- i to  n){results(index) = x.toByte ; find_combinations_sum_n(x.toByte, (n - x).toByte, results, (index.toByte + 1.toByte).toByte)}
  }

  def permute_and_palendrome(toPermute: ArrayBuffer[Byte]) {
    if(DEBUG) for(x <- toPermute.permutations) if(isPalindrome(x)){
      count_palindromes += 1
      print(x.toSeq);if(x.contains(m)) {print(" <- contains " + m + "\n")}else println }

    if (toPermute.contains(m)){
      for (el <- toPermute.permutations) if(isPalindrome(el)){
          count_matching_m += 1
          if(informed) {pw.write(el.toString() + "\n")}
      }
    }
  }
  


def isPalindrome(permutedSums: ArrayBuffer[Byte]): Boolean = {
    val len = permutedSums.length
    for(i <- 0 until len/2) {
      if(permutedSums(i) != permutedSums(len-i-1)){return false}
    }
    true
}

} // end of Palindromes class


object Palindromes {

  val DEBUG = true

  val usage =
          """

            Use: java PalindromesSearch n m [y]
            [y]: when informed, all palindromic sequences must be saved to a file

          """
  val OUTPUT_FILE_NAME = "output.txt"

  var informed = false
  var count_palindromes = 0
  var count_matching_m = 0
  val t = System.nanoTime()

  def main(args: Array[String]): Unit = {

    print("\n\nWelcome to the palindromic sequence project!\n\n")
    if (args.length <= 1){
      println(usage)
      System.exit(1)
    }
    val n =  args(0).toByte
    val m = args(1).toByte
    if (args.length == 3 && args(2) == "y")
      informed = true
    else
      informed = false

    print("Parameter n = " + n + "\nParameter m = " + m + "\n\n\n")

    new Palindromes(n, m, informed)
  } // end main method
} // end of object
