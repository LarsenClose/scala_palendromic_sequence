import scala.collection.mutable._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ArrayBuffer._
import scala.collection.Seq
import scala.collection.Seq._

import scala.math
import java.io.File
import java.io.PrintWriter


import scala.io.Source

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


object PalindromesSearch {
  
  class PalindromesSearch (private var n: Int, m: Int) {



  def divisorsSequence(n: Int): Seq[(Int)] = {
    val divise = new ArrayBuffer[Int]
    // var upperBound = math.sqrt(n+2).toInt

    for (d <- 1 to n-1)                                 
      if (n % d==0)
        divise += (d)
    divise.toSeq
  }

  // def combinationPossibilities


  // def multi[Seq](as: Seq[Int], n: Int): Seq[Seq[Int]] = 
  //   (Seq.fill(n)(as)).flatten.combinations(n).toList

  // def factorCombinationsSummingTotal(fun: Int => Int, low: Int, high: Int): Seq[(Int, Int)] = {
  //   val seq = new ArrayBuffer[(Int, Int)]
  //   for (value <- low to high)
  //     seq += ((value, fun(value)))
  //   seq.toSeq
  // }

    // def factorCombinationsSummingTotal(seqFactor: Seq[(Int)]): Seq[Seq[(Int)]] = {
    def sumCombination(divisors: Seq[(Int)]): Seq[Seq[(Int)]] = {
      var comb = Seq[(Int)]()
      var combs =  Seq[Seq[(Int)]]()

      for( i <- 0 to (divisors.length - 1)){
        combs +: oneCombo(divisors, i)
      }
      // combs += divisors.foreach(oneCombo(divisors, _))
      

      // for( i <- 1 to (incoming.length - 1)){
      //   while (n !=  combs(i).reduceLeft(_ + _)){
      //     combs(i) += incoming(i)
      //     if (n == combs(i).reduceLeft(_ + _))
            
      //   }

      // }
      combs.toSeq
    }

    def oneCombo(divisors: Seq[(Int)], start: Int): Seq[(Int)] = {

      var oneComboSum = new ArrayBuffer[(Int)]
      oneComboSum += (divisors(start))

      for( i <- 0 to (divisors.length - 1) by 1){
        while (n >  oneComboSum.reduceLeft(_ + _)){
            if (n == oneComboSum.reduceLeft(_ + _)){
              return oneComboSum.toSeq
            }
          for (j <- (divisors.length - 1) to 0 by -1){
            if (n > oneComboSum.reduceLeft(_ + _) + divisors(j) ){
              oneComboSum += (divisors(j))
              if (n == oneComboSum.reduceLeft(_ + _)){
                return oneComboSum.toSeq
            }
            }
          }
          if (n > oneComboSum.reduceLeft(_ + _) + divisors(i) ){
            oneComboSum += (divisors(i))
            if (n == oneComboSum.reduceLeft(_ + _)){
              return oneComboSum.toSeq
            }
          }
        }
      }
      oneComboSum.toSeq


    }

      //     sequences += comb

      // sequences




      // while(seqFactor.hasNext)
      //   comb += seqFactor(index)
      //   number -= seqFactor(index)
      //   while(number-factor >=0)
      //     comb += seqFactor(index)
      //      number -= seqFactor(index)
      //   seqFactor.next()
      //   index += 1
      //   if (number == 0)
      //     // sequences.add(comb.toSeq) 
      //     print(comb.toSeq)

      // sequences
       
        



  // }


  def write(args: Array[String]) {
    val writer = new PrintWriter(new File(OUTPUT_FILE_NAME))

    writer.write("Hello Developer, Welcome to Scala Programming.")
    writer.close()

    Source.fromFile("Write.txt").foreach { x => print(x) }
  }








} // end PalindromesSearch class


  val usage = 
          """

            Use: java PalindromesSearch n m [y]
            [y]: when informed, all palindromic sequences must be saved to a file 

          """
  val OUTPUT_FILE_NAME = "output.txt"

  var informed = false

  def main(args: Array[String]): Unit = {

    print("\n\nWelcome to the palindromic sequence project!\n\n")
    if(args.length == 0)
      sys.exit(1)

    if (args.length == 2) 
      println(usage)
      
    val n = args(0).toInt
    val m = args(1).toInt
    if (args.length == 3 && args(2) == "y")
      informed = true

    print("Parameter n = " + n + "\nParameter m = " + m + "\n")

    val drome = new PalindromesSearch(n, m)
    var divisors =  drome.divisorsSequence(n).toSeq
    var combi =  drome.sumCombination(divisors)

    print(divisors+ "\n")

    


    // divisors.multi(6).foreach(println _)

    

    print(combi+ "\n")

    // combi.foreach(println _)

    
  } // end main method

} // end of object


  // def isPalindrome[A](l: List[A]):Boolean = {
  //   def _palindrome(res: Boolean, rem: List[A]): Boolean = rem match {
  //       case Nil => res"Write.txt"
  //       case frl(first, last, rem) => _palindrome(res && first == last, rem)
  //   }
  //   _palindrome(true, l)
  // }
 


        //   def commaSeparated(list: List[String], func: String=>Unit): Unit = list match {
        //   case List() => 
        //   case List(a) => func(a)
        //   case h::t => func(h + ", ")
        //               commaSeparated(t, func)
        // }


    // val evensOnly = array.filter(_ % 2 == 0)

    // for (d <- 2 to n - 1)


 // PalindromesSearch object
