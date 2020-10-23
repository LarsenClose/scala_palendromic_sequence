package sparkyThePalindrome


import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ArraySeq
import java.io.File
import java.io.PrintWriter

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.sql.SparkSession


import scala.collection.parallel._
import scala.collection.parallel.mutable.ParArray
import scala.collection.parallel.mutable.ParSeq

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD





/*
 * CS3210 - Principles of Programming Languages - Fall 2020
 * Instructor: Thyago Mota
 * Description: Prg02 - PalindromesSearch
 * Student: Larsen Close and Stuart Griffin
 *
 * TODO
 * 1. Speed it up
 */



object PalindromesSpark {

  // val spark = SparkSession.builder().appName("sparkyThePalindrome").enableHiveSupport().getOrCreate()

  // val sc = spark.sparkContext

  def main(args: Array[String]): Unit = {


      val DEBUG = false
      val usage =
              """

                Use: java PalindromesSearch n m [y]
                [y]: when informed, all palindromic sequences must be saved to a file

              """
      val OUTPUT_FILE_NAME = "output.txt"

      var informed = false
      var count_palindromes: Double = 0
      var count_matching_m: Double = 0
      val t = System.nanoTime()


        print("\n\nWelcome to the palindromic sequence project!\n\n")
        if (args.length <= 1){
          println(usage)
          System.exit(1)
        }


        val n =  args(0).toInt
        val m = args(1).toInt
        if (args.length == 3 && args(2) == "y")
          informed = true
        else
          informed = false

        print(f"Parameter n = $n%f \nParameter m = $m%f + \n\n\n")


  
      val sc = new SparkContext(new SparkConf().setMaster("local[*]").setAppName("sparkyThePalindrome"))
      val data = sc.parallelize(Array.fill(n)(0))   
      val rdd = sc.parallelize(find_combinations_sum_n(1,n,data, 0))


     


      val pw = new PrintWriter(new File("tmp.txt" ))


      if (informed){
        println("Generating palindromic sequences...\n\n\n")
        pw.write(rdd)
        pw.close
        println("\n\nDone!\n\n")
        if(DEBUG){printStatistics()}
      }
      else {
        print(rdd)
        printStatistics()
      }

      def printStatistics()  {
        if (DEBUG)println(f"\nTotal number of palindromic sequences found: $count_palindromes%f")
        println(f"\nNumber of palindromic sequences found in sequences to $n%f which contain $m%f is: $count_matching_m%f")
        val timeEnds: Double = ((System.nanoTime() - t) / scala.math.pow(10, 9))
        println(f"\nDuration: $timeEnds%f \n")
        
      }


    def find_combinations_sum_n (i: Int, n: Int, distData: RDD[Int], index: Int) {
      // base case result (take) index contains a combinatorial summation equal to n
      if (n == 0) { permute_and_palendrome(distData.slice(index)) } //;print(distData.take(index))
      var x = i

      data.collect().foreach(x => ({
        results(index) = x  
        find_combinations_sum_n(x, (n - x), results, (index + 1))})
    }

  def permute_and_palendrome(toPermute: ArrayBuffer[Int]) {
    // if(DEBUG) for(x <- toPermute.permutations) if(isPalindrome(x)){
    //   count_palindromes += 1
    //   print(x.toSeq);if(x.contains(m)) {print(" <- contains " + m + "\n")}else println }

    if (toPermute.contains(m)){
      // var hasEvenparity = toPermute.size % 2 == 0
      // var frequency = toPermute.groupMapReduce(identity)(_ => 1)(_ + _) 
      // var parityElem = frequency(2) % 2 == 0
      // if ( parityElem && hasEvenparity || !parityElem && !hasEvenparity){
      
        for (el <- toPermute.permutations) if(isPalindrome(el)){
            count_matching_m += 1
            if(informed) {pw.write(el.toString() + "\n")}
        }
      }
    }
  



def isPalindrome(permutedSums: ArrayBuffer[Int]): Boolean = {
      val len = permutedSums.length
      for(i <- 0 until len/2; j <- len/2 to i by -1) {
        if((permutedSums(i) != permutedSums(len-i-1)) || (permutedSums(len/2 +i) != permutedSums((len/2-1) + j)))  {return false}

      }
      true
  }


    } // end of Palindromes class
  
  

} // end of object
