
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






/*
 * CS3210 - Principles of Programming Languages - Fall 2020
 * Instructor: Thyago Mota
 * Description: Prg02 - PalindromesSearch
 * Student: Larsen Close and Stuart Griffin
 *
 * TODO
 * 1. Speed it up
 */




class Palindromes (private var n: Int, private var m: Int, informed: Boolean) {

  def results() = ArrayBuffer.fill(n)(0)
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
    println("\nNumber of palindromic sequences found which contain " + m +  " is: " + count_matching_m)
    printf("\nDuration: %.5fs\n", (System.nanoTime() - t) / scala.math.pow(10, 9))
  }


  def find_combinations_sum_n (i: Int, n: Int, results: ArrayBuffer[Int], index: Int) {
    // base case result (take) index contains a combinatorial summation equal to n
		if (n == 0) { permute_and_palendrome(results.take(index)) }
    var x: Int = i
    for (x <- i to  n){results(index) = x ; find_combinations_sum_n(x, n - x, results, index + 1)}
  }

  def permute_and_palendrome(toPermute: ArrayBuffer[Int]) {
    for(x <- toPermute.permutations) if (x.contains(m)) {
      if (isPalindrome(x) && x.contains(m)){
        // if(DEBUG) {print(x.to[ArrayBuffer])if(x.contains(m)) {print(" <- contains " + m + "\n")}else println }

          count_matching_m += 1
          if(informed){pw.write(x.toString() + "\n")}

        }
      }
    }

def isPalindrome(permutedSums: ArrayBuffer[Int]): Boolean = {
    val len = permutedSums.length
    for(i <- 0 until len/2) {
      if(permutedSums(i) != permutedSums(len-i-1)){return false}
    }
    true
  }

} // end of Palindromes class


object Palindromes {

  val DEBUG = false

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
    else
      informed = false

    print("Parameter n = " + n + "\nParameter m = " + m + "\n\n\n")

    new Palindromes(n, m, informed)
  } // end main method
} // end of object


// /**
//  * Generic way to create memoized functions (even recursive and multiple-arg ones)
//  * Author pathikrit stackoverflow
//  * @param f the function to memoize
//  * @tparam I input to f
//  * @tparam K the keys we should use in cache instead of I
//  * @tparam O output of f
//  */
// case class Memo[I <% K, K, O](f: I => O) extends (I => O) {
//   import collection.mutable.{Map => Dict}
//   type Input = I
//   type Key = K
//   type Output = O
//   val cache = Dict.empty[K, O]
//   override def apply(x: I) = cache getOrElseUpdate (x, f(x))
// }

// object Memo {
//   /**
//    * Type of a simple memoized function e.g. when I = K
//    */
//   type ==>[I, O] = Memo[I, I, O]
// }





// lazy val fib: Int ==> BigInt = Memo {
//   case 0 => 0
//   case 1 => 1
//   case n if n > 1 => fib(n-1) + fib(n-2)
// }


//   /**
//    * http://mathworld.wolfram.com/Combination.html
//    * @return memoized function to calculate C(n,r)
//    */
//   val c: (Int, Int) ==> BigInt = Memo {
//     case (_, 0) => 1
//     case (n, r) if r > n/2 => c(n, n - r)
//     case (n, r) => c(n - 1, r - 1) + c(n - 1, r)
//   }


//   /**
//    * Calculate edit distance between 2 sequences
//    * O(s1.length * s2.length)
//    *
//    * @return Minimum cost to convert s1 into s2 using delete, insert and replace operations
//    */
//   def editDistance[A](s1: Seq[A], s2: Seq[A]) = {

//     type DP = Memo[(Seq[A], Seq[A]), (Int, Int), Int]
//     implicit def encode(key: DP#Input): DP#Key = (key._1.length, key._2.length)

//     lazy val f: DP = Memo {
//       case (a, Nil) => a.length
//       case (Nil, b) => b.length
//       case (a :: as, b :: bs) if a == b => f(as, bs)
//       case (a, b) => 1 + (f(a, b.tail) min f(a.tail, b) min f(a.tail, b.tail))
//     }

//     f(s1, s2)
//   }
