import scala.collection.mutable.ArrayBuffer
import scala.math





/*
 * CS3210 - Principles of Programming Languages - Fall 2020
 * Instructor: Thyago Mota
 * Description: Prg02 - PalindromesSearch
 * Student: Larsen Close
 *
 * TODO
 * 1. Sanitize input
 * 2. Find all divisors
 * 3. Find all permutations of divisors which equal n
 * 4. Eliminate all permutations which are not palindromes (first) or which cannot be palindromes (optimization)
 */




object PalindromesSearch {
  val usage = 
          """
            Use: java PalindromesSearch n m [y]
            [y]: when informed, all palindromic sequences must be saved to a file
          """
  val OUTPUT_FILE_NAME = "output.txt"

  var informed = false

  def main(args: Array[String]): Unit = {

    print("Welcome to the palindromic sequence project!")
    args.foreach(println(_))
    println(args.length + " length")
    // if (args.length < 2 || args.length > 3) 
        if (args.length == 0) 
      println(usage)
      sys.exit(1)
    val n = args(0)
    val m = args(1)
    if (args.length == 2 && args(2) == "y")
      informed = true


    // println("Parameter n = " + n + "\nParameter m = " + m)


    
    
  }


  // def isPalindrome[A](l: List[A]):Boolean = {
  //   def _palindrome(res: Boolean, rem: List[A]): Boolean = rem match {
  //       case Nil => res
  //       case frl(first, last, rem) => _palindrome(res && first == last, rem)
  //   }
  //   _palindrome(true, l)
  // }



    def sequenceDivisors(n: Int): Seq[(Int)] = {
      val seq = new ArrayBuffer[(Int)]
      for (d <- 1 to n - 1)                 // TODO: optimize for 1 to sqrt(n)
          seq += d
      return seq
    }


    // val evensOnly = array.filter(_ % 2 == 0)

    // for (d <- 2 to n - 1)


}
