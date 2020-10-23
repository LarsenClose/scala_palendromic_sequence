package pallyParks


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

  val spark = SparkSession.builder().appName("pallyParks").enableHiveSupport().getOrCreate()

  val sc = spark.sparkContext

  def main(args: Array[String]): Unit = {
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

        print(f"Parameter n = $n%f \nParameter m = $m%f + \n\n\n")

  
      val data = Array.fill(n)(0.toByte).par

      val combine = find_combinations_sum_n(1 , n , data, 0)

      val combineData =  sc.parallelize(combine)





      val pw = new PrintWriter(new File("tmp.txt" ))


      if (informed){
        println("Generating palindromic sequences...\n\n\n")
        pw.write(find_combinations_sum_n(1,n,data, 0).toString())
        pw.close
        println("\n\nDone!\n\n")
        if(DEBUG){printStatistics()}
      }
      else {
        find_combinations_sum_n(1,n,data, 0)
        printStatistics()
      }

      def printStatistics()  {
        if (DEBUG)println(f"\nTotal number of palindromic sequences found: $count_palindromes%f")
        println(f"\nNumber of palindromic sequences found in sequences to $n%f which contain $m%f is: $count_matching_m%f")
        val timeEnds: Double = ((System.nanoTime() - t) / scala.math.pow(10, 9))
        println(f"\nDuration: $timeEnds%f \n")
        
      }


      def find_combinations_sum_n (i: Byte, n: Byte, distData: ParArray[Byte], index: Byte) {
        // base case result (take) index contains a combinatorial summation equal to n
        if (n == 0) {(distData.take(index).to[ParArray])} //;print(distData.take(index))
        var x: Byte = i
        for (x <- i to  n){distData(index) = x.toByte ; find_combinations_sum_n(x.toByte, (n - x).toByte, distData, (index.toByte + 1.toByte).toByte)}
      }


      def find_combinations_sum_n (i: Byte, n: Byte, distData: ParArray[Byte], index: Byte) {
        // base case result (take) index contains a combinatorial summation equal to n
        if (n == 0) { permute_and_palendrome(distData.take(index).to[ParArray]) } //;print(distData.take(index))
        var x: Byte = i
        for (x <- i to  n){distData(index) = x.toByte ; find_combinations_sum_n(x.toByte, (n - x).toByte, distData, (index.toByte + 1.toByte).toByte)}
      }

      def permute_and_palendrome(toPermute: ParArray[Byte]) {
        // if(DEBUG) for(x <- toPermute.permutations) if(isPalindrome(x)){
        //   count_palindromes += 1
        //   print(x.toSeq);if(x.contains(m)) {print(" <- contains " + m + "\n")}else println }

        if (toPermute.exists(_ == m)){
          for (el <- toPermute.seq.permutations) if(isPalindrome(el)){
              count_matching_m += 1
              if(informed) {pw.write(el.toString() + "\n")}
          }
        }
      }
      
      // def permuteParallel(xs:ParSeq[Byte]):ParSeq[ParSeq[Byte]] = xs match {
      // case isEmpty => ParSeq(ParSeq())
      // case _  => {
      //   val len = xs.length
      //   val lTr = (0 to len-1).map(xs.splitAt(_)).toSeq.filter(lT => !lT._1.exists(_ == lT._2.head)) 
      //   lTr.map(lT => permuteParallel(lT._1++lT._2.tail).map(lT._2.head ++ _)).flatten
      //   }
      // } 




    def isPalindrome(permutedSums: ArraySeq[Byte]): Boolean = {
        val len = permutedSums.length
        for(i<- 0 until len/2) {
          if(permutedSums(i) != permutedSums(len-i-1)){return false}
        }
        true
    }

    } // end of Palindromes class
  

} // end of object
