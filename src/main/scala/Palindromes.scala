


package scala
package collection

import immutable.{ List, Range }
import generic._
import parallel.ParSeq
import scala.math.Ordering

import Palindromes._
import scala.collection.mutable._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ArrayBuffer._

import scala.compat.Platform.currentTime

import scala.math
import java.io.File
import java.io.PrintWriter
import java.io._


import scala.io.Source
import scala.collection.parallel.mutable.ParArray
import java.io.SequenceInputStream

import java.util.Arrays;
import java.util.stream.Collectors;






/*
 * CS3210 - Principles of Programming Languages - Fall 2020
 * Instructor: Thyago Mota
 * Description: Prg02 - PalindromesSearch
 * Student: Larsen Close
 *
 * TODO
 * 1. Sanitize input
 * 2. Find all divisors
 * 3. Find all combinations of divisors which equal n
 * 4. Eliminate all permutations which are not palindromes (first) or which cannot be palindromes (optimization)
 */




class Palindromes (private var n: Int, private var m: Int, informed: Boolean) {

    var results = ArrayBuffer.fill(n)(0)
    val pw = new PrintWriter(new File("tmp.txt" ))

    if (informed){
      println("Generating palindromic sequences...\n\n\n")
      pw.write(find_combinations_sum_n(1,n,results, 0).toString())
      pw.close
      if(DEBUG){printStatistics()}
    }
    else {
      find_combinations_sum_n(1,n,results, 0)
      printStatistics()
    }

    
    def printStatistics()  {
      println("\nTotal number of palindromic sequences found: " + count_palindromes)
      println("\nNumber of palindromic sequences found which contain " + m +  " is: " + count_matching_m)
      printf("\nDuration: %.5fs\n", (System.nanoTime() - t) / scala.math.pow(10, 9))
    }               




  def find_combinations_sum_n (i: Int, n: Int, reults: ArrayBuffer[Int], index: Int) {
    // base case result (take) index contains a combinatorial summation equal to n 
		if (n == 0) { permute_and_palendrome(results.to[ArrayBuffer].take(index))}
    var x: Int = i 
    for (x <- i to  n){results(index) = x ; find_combinations_sum_n(x, n - x, results, index + 1)}
  }



  def permute_and_palendrome(toPermute: ArrayBuffer[Int]) {
    for(x <- toPermute.permutations.to[ArrayBuffer]) {
      if (isPalindrome(x)){

        if(DEBUG) {println(x.to[ArrayBuffer])}

        count_palindromes += 1
        if(informed){pw.write(x.toString() + "\n")}
      
      }
    }
  }
        

def isPalindrome(permutedSums: ArrayBuffer[Int]): Boolean = {
    val len = permutedSums.length
    for(i <- 0 until len/2) {
      if(permutedSums(i) != permutedSums(len-i-1)){
        return false}
    }
    true 
  }            
}
  

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
    if(args.length == 0){
      println(usage)
      System.exit(1)
    }
    if (args.length == 1){
      println(usage)
      System.exit(1)
    }
    val n =  args(0).toInt
    val m = args(1).toInt
    if (args.length == 3 && args(2) == "y")
      informed = true

    print("Parameter n = " + n + "\nParameter m = " + m + "\n\n\n")

    val dromes  = new Palindromes(n, m, informed)
  } // end main method
} // end of object