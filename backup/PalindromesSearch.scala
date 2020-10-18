import PalindromesSearch._
import scala.collection.mutable._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ArrayBuffer._
import scala.collection.mutable.Seq
import scala.collection.mutable.Seq._
import scodec._
import scodec.bits._

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



  
class PalindromesSearch (private var n: Int, m: Int) {



  // def divisorsSequence(n: Int): Seq[(Int)] = {
  //   val divise = new ArrayBuffer[Int]
  //   // var upperBound = math.sqrt(n+2).toInt

  //   for (d <- 1 to n-1)                                 
  //     if (n % d==0)
  //       divise += (d)
  //   (divise += n).toSeq
  //   }

  def sub(a: Int, b: Int) = a - b 

  def double(el: Int) = el * 2

    //  returns list of n's divisors
  def divSeq(n: Int): ArrayBuffer[Int]  = (1 to n + 1).filter(n % _ == 0).to[ArrayBuffer]

  def removeFirstAndLast[A](xs: Iterable[A]) = xs.drop(1).dropRight(1)


  //   // called with divsiros returns an array of tuples (divisor,multiple) = n
  // def divMult(n: Int) = divSeq(n).map((f: Int) => { (f, ndiv(n, f)) })


  // def divVary(n: Int) = divSeq(n).filter( _ > 1).filter(double(_) != n ).filter(_ < n).map((f: Int) =>  (f, sub(n, f)) )


      // called with divsiros returns an array of tuples (divisor,multiple) = n
  def divMult(n: Int): ArrayBuffer[(Int, Int)]= divSeq(n).map((f: Int) => { (f, ndiv(n, f)) })


  def divVary(n: Int): ArrayBuffer[(Int, Int)] = divSeq(n).filter( _ > 1).filter(double(_) != n ).filter(_ < n).map((f: Int) => { (f, sub(n, f)) })

  



    // returns a list of lists of all the combinations of size n within sequence p
  def comb(p: ArrayBuffer[Int], n:Int)  = ((0 until p.size).to[ArrayBuffer].combinations(n).map{ _ map p })


    // returns a list of length a containing int b
  def listDiv(a: Int, b: Int): ArrayBuffer[Int]  =  ArrayBuffer.fill(a)(b)

  // def listDov(n: Int, substitute: Int): ArrayBuffer[Int]  = {
  //        ArrayBuffer.fill()(substitute)
  

  // }
  
  



  //   // returns a list of length a containing int b
  // def listVary(a: Int, b: Int) = List.fill(a)(b)

  // def add[(_: Int, _: Int)] = a + b





  def divFactComb[N](N: ArrayBuffer[(Int, Int)], n: Int) = {divMult(n).map{case (param1, param2) => listDiv(param1,param2)}}

  def divVaryComb[N](N: ArrayBuffer[(Int, Int)], n: Int)  = (divVary(n).map{case (param1, param2)  => (listDiv( param1, 1 )) :+ (param2)})

  // removeFirstAndLast
  


  // def divVaryComb[N](N: Seq[(Int, Int)], n: Int, z: Int) = divVary(n).map{case (param1, param2) => (listDiv(param1,z) :+ (param2))}


  // def divVaryComb[N](N: Seq[(Int, Int)], n: Int) = divVary(n).map{case (param1, param2) => while ( i=0,i++; i  until ndiv(d, n - param1)) listDiv(param1,1) :+ (param2)}

    // returns the number of times a number a can be multiplied and remain less than n
  def ndiv(n: Int, a: Int) = {
    var times = 0
    var temp = a
    while (temp <= n) {
      times += 1
      temp += a
    }
    times
  }

  def combo(n: Int, divisors: ArrayBuffer[Int] ): ArrayBuffer[Int]  = {
	  val combos =  ArrayBuffer.fill(n + 1)(0)
	  combos(0) = 1
	  divisors.foreach (current =>
	  for (i<-current to n)
		  combos(i) =  combos(i) + combos(i - current)
		  )
	combos
  }   


  // def write(args: Array[String]) {
  //   val writer = new PrintWriter(new File(OUTPUT_FILE_NAME))

  //   writer.write("Hello Developer, Welcome to Scala Programming.")
  //   writer.close()

  //   Source.fromFile("Write.txt").foreach { x => print(x) }
  // }



  }


object PalindromesSearch {

  val usage = 
          """

            Use: java PalindromesSearch n m [y]
            [y]: when informed, all palindromic sequences must be saved to a file 

          """
  val OUTPUT_FILE_NAME = "output.txt"

  var informed = false

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

    print("Parameter n = " + n + "\nParameter m = " + m + "\n")

    val drome  = new PalindromesSearch(n, m)
    var divisors: ArrayBuffer[Int]   =   drome.divSeq(n)
    // var mult = drome.divMult(n)

    // println(drome.primeFactorsMult(336))

    

    // var combi =  drome.sumCombination(divisors)
    // var divMult = drome.divisorsMult(n)

    // print(drome.divisorsSequence(n).toSeq + "\n")

    // print(div2+ "\n"+ "\n")

    println("\n\ndivisor Seq func")
    println(drome.divSeq(n))

    println("\n\ndivisor Mult func")
    println(drome.divMult(n))

    // println("\n\ndivisor Mult func unzip")
    // println(drome.divMult(n).unzip)

    var conbinations = drome.divFactComb(drome.divMult(n), n) :+ drome.divVaryComb(drome.divVary(n),n)


    // println("\n\ndivFactList func")
    // drome.divFactComb(drome.divMult(n), n) foreach println
    // println("end divFactList func end")
    // print(drome.divFactComb(drome.divMult(n), n))

    // println("\n\ndivisor Vary func")
    // println(drome.divVaryComb(drome.divVary(n),n))



    
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
