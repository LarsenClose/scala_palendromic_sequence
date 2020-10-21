


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
import scala.collection.mutable.Seq
import scala.collection.mutable.Seq._
import scala.collection.parallel._
import scodec._
import scodec.bits._
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




class Palindromes (private var n: Int, private var m: Int) {



  val x = ( 1 to n).to[ArrayBuffer]

  // val xr = List.fill(n)(x).flatten.to[ArrayBuffer]
  // println(xr)


//   def getCombos(xr: Seq[Int], upTo: Int) = {
//     for(b <- 1 to upTo){
      

//          println(xr.combinations(b).to[ArrayBuffer])

//     }
//   }
// getCombos(xr, n)
  val zz = x.permutations
  
  val uu = for (ind <- 1 to n) {x.combinations(ind).to[ArrayBuffer]}

  print(uu)





  def find_combinations_sum_n (i: Int, n: Int, reults: ArrayBuffer[Int], index: Int) {

		if (n == 0) {

      permute_and_palendrome(results.to[ArrayBuffer].take(index))
		}
    
    // start from previous element in the combination till n
    var x: Int = i 
		for (x <- i to  n  )
		{ 

      
			// place current element at current index
			results(index) = x
      
			// recur with reduced sum
			find_combinations_sum_n(x, n - x, results, index + 1);
		}
  }



  def permute_and_palendrome(toPermute: ArrayBuffer[Int]) {
    for(x <- toPermute.permutations.to[ArrayBuffer]) {
      if (isPalindrome(x)){
        println(x.to[ArrayBuffer])
        pw.write(x.toString() + "\n")
      
      }
    }
  }
        

def isPalindrome(permutedSums: ArrayBuffer[Int]): Boolean = {
    val len = permutedSums.length
    for(i <- 0 until len/2) {
      if(permutedSums(i) != permutedSums(len-i-1)){
        return false
      }
    }
    true 
  }

    var results = ArrayBuffer.fill(n)(0)
    val pw = new PrintWriter(new File("tmp.txt" ))

    pw.write(find_combinations_sum_n(1,n,results, 0).toString())

    pw.close


  find_combinations_sum_n(1,n,results, 0)


  // x.combinations(4).to[ArrayBuffer]

  // println(x.toSet[Int].subsets.map(_.toList).toList.combinations(4))


  // var z = y.combinations(3).foreach(println)


  // println(z)
  // y.permutations foreach println


  // for (w <- 0 to n){
  //   println((x^2).combinations(w))
  //   println("\n\n\n")
  //   }

  // def comboLen(n: Int) =  comb(arrayN(n), n).foreach{println}

  // def comboLen(n: Int) = for (len <-1 to n){ comb(arrayN(n), len).foreach{println}}

  // def combs(n: Int) =  for(_ <-1 until arr.length){ comb(arr, _)}

  // def isPalindromeREALLY(seq: ArrayBuffer[Int], func: Int => Boolean) = {
  //     for (d <- 1 to n-1){
  //       if(seq(d) == seq(n-d)){
  //         true
  //       }
  //     }
  //   false
  // )




    // def isPalindrome(seq: ArrayBuffer[Int] => Boolean) = {( 1 until length(seq)/2) => (_==_)}

  def sumToN(seq: ArrayBuffer[Int], func: Int => Boolean)  = (seq.foldLeft(0)(_ + _) == n)

  // def isPalindrome(seqSeq: ArrayBuffer[ArrayBuffer[Int]]) = (
  //   while (seqSeq.hasnext) { seq = seqSeq.next
  //      while (seq.hasnext){(index = seq.next) 
  //       if (seq(index) != seq(seq.length-index)){
  //         seq.clear
  //      }

  //   }

  // }


    // def comb(p: ArrayBuffer[Int], n:Int)   = p.combinations(n).map{ _ map p }
  // def comb(p: ArrayBuffer[Int], n:Int)  = p.combinations(n).map{ _ map p }.toArray.par).foreach{println}






  // def isPalindrome(seq: ArrayBuffer[Int] => Boolean) = {( 1 until length(seq)/2) => (_==_)}
  //
  // def correctSum(seq: ArrayBuffer[Int]): Boolean  = {
  //   if (seq.foldLeft(0)(_ + _) == n){
  //     true
  //   }
  //   false
  // }

    // returns a list of lists of all the combinations of size n within sequence p
  def comb(p: ArrayBuffer[Int], n:Int)  = ((0 until p.size).to[ArrayBuffer].combinations(n).map{ _ map p })


// def divFactComb[N](N: ArrayBuffer[(Int, Int)], n: Int) = {divMult(n).map{case (param1, param2) => listDiv(param1,param2)}}

// def divVaryComb[N](N: ArrayBuffer[(Int, Int)], n: Int) = {(divVary(n).map{case (param1, param2)  => listDiv( param1, 1 ) :+ (param2)})}


def writeFile(filename: String, lines: Seq[String]): Unit = {
    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file))
    for (line <- lines) {
        bw.write(line)
    }
    bw.close()
}
                                                            

}
  


object Palindromes {

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

    val dromes  = new Palindromes(n, m)
 






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



class overSeq {

trait SeqLike[+A, +Repr] extends Any with IterableLike[A, Repr] with GenSeqLike[A, Repr] with Parallelizable[A, ParSeq[A]] { self =>

  override protected[this] def thisCollection: Seq[A] = this.asInstanceOf[Seq[A]]
  override protected[this] def toCollection(repr: Repr): Seq[A] = repr.asInstanceOf[Seq[A]]

  def length: Int

  def apply(idx: Int): A

  protected[this] override def parCombiner = ParSeq.newCombiner[A]

  /** Compares the length of this $coll to a test value.
   *
   *   @param   len   the test value that gets compared with the length.
   *   @return  A value `x` where
   *   {{{
   *        x <  0       if this.length <  len
   *        x == 0       if this.length == len
   *        x >  0       if this.length >  len
   *   }}}
   *  The method as implemented here does not call `length` directly; its running time
   *  is `O(length min len)` instead of `O(length)`. The method should be overwritten
   *  if computing `length` is cheap.
   */
  def lengthCompare(len: Int): Int = {
    if (len < 0) 1
    else {
      var i = 0
      val it = iterator
      while (it.hasNext) {
        if (i == len) return if (it.hasNext) 1 else 0
        it.next()
        i += 1
      }
      i - len
    }
  }

  override /*IterableLike*/ def isEmpty: Boolean = lengthCompare(0) == 0

  /** The size of this $coll, equivalent to `length`.
   *
   *  $willNotTerminateInf
   */
  override def size = length

  def segmentLength(p: A => Boolean, from: Int): Int = {
    var i = 0
    val it = iterator.drop(from)
    while (it.hasNext && p(it.next()))
      i += 1
    i
  }

  def indexWhere(p: A => Boolean, from: Int): Int = {
    var i = math.max(from, 0)
    val it = iterator.drop(from)
    while (it.hasNext) {
      if (p(it.next())) return i
      else i += 1
    }
    -1
  }

  // def lastIndexWhere(p: A => Boolean, end: Int): Int = {
  //   var i = length - 1
  //   val it = reverseIterator
  //   while (it.hasNext && { val elem = it.next(); (i > end || !p(elem)) }) i -= 1
  //   i
  // }

  /** Iterates over distinct permutations.
   *
   *  @return   An Iterator which traverses the distinct permutations of this $coll.
   *  @example  `"abb".permutations = Iterator(abb, bab, bba)`
   */
  def permutations: Iterator[Repr] =
    if (isEmpty) Iterator(repr)
    else new PermutationsItr

  /** Iterates over combinations.  A _combination_ of length `n` is a subsequence of
   *  the original sequence, with the elements taken in order.  Thus, `"xy"` and `"yy"`
   *  are both length-2 combinations of `"xyy"`, but `"yx"` is not.  If there is
   *  more than one way to generate the same subsequence, only one will be returned.
   *
   *  For example, `"xyyy"` has three different ways to generate `"xy"` depending on
   *  whether the first, second, or third `"y"` is selected.  However, since all are
   *  identical, only one will be chosen.  Which of the three will be taken is an
   *  implementation detail that is not defined.
   *
   *  @return   An Iterator which traverses the possible n-element combinations of this $coll.
   *  @example  `"abbbc".combinations(2) = Iterator(ab, ac, bb, bc)`
   */
  def combinations(n: Int): Iterator[Repr] =
    if (n < 0 || n > size) Iterator.empty
    else new CombinationsItr(n)

  private class PermutationsItr extends AbstractIterator[Repr] {
    private[this] val (elms, idxs) = init()
    private var _hasNext = true

    def hasNext = _hasNext
    def next(): Repr = {
      if (!hasNext)
        Iterator.empty.next()

      val forcedElms = new mutable.ArrayBuffer[A](elms.size) ++= elms
      val result = (self.newBuilder ++= forcedElms).result()
      var i = idxs.length - 2
      while(i >= 0 && idxs(i) >= idxs(i+1))
        i -= 1

      if (i < 0)
        _hasNext = false
      else {
        var j = idxs.length - 1
        while(idxs(j) <= idxs(i)) j -= 1
          swap(i,j)

        val len = (idxs.length - i) / 2
        var k = 1
        while (k <= len) {
          swap(i+k, idxs.length - k)
          k += 1
        }
      }
      result
    }
    private def swap(i: Int, j: Int) {
      val tmpI = idxs(i)
      idxs(i) = idxs(j)
      idxs(j) = tmpI
      val tmpE = elms(i)
      elms(i) = elms(j)
      elms(j) = tmpE
    }

    private[this] def init() = {
      val m = mutable.HashMap[A, Int]()
      val (es, is) = (thisCollection map (e => (e, m.getOrElseUpdate(e, m.size))) sortBy (_._2)).unzip

      (es.toBuffer, is.toArray)
    }
  }

  private class CombinationsItr(n: Int) extends AbstractIterator[Repr] {
    // generating all nums such that:
    // (1) nums(0) + .. + nums(length-1) = n
    // (2) 0 <= nums(i) <= cnts(i), where 0 <= i <= cnts.length-1
    private val (elms, cnts, nums) = init()
    private val offs = cnts.scanLeft(0)(_ + _)
    private var _hasNext = true

    def hasNext = _hasNext
    def next(): Repr = {
      if (!hasNext)
        Iterator.empty.next()

      /* Calculate this result. */
      val buf = self.newBuilder
      for(k <- 0 until nums.length; j <- 0 until nums(k))
        buf += elms(offs(k)+j)
      val res = buf.result()

      /* Prepare for the next call to next. */
      var idx = nums.length - 1
      while (idx >= 0 && nums(idx) == cnts(idx))
        idx -= 1

      idx = nums.lastIndexWhere(_ > 0, idx - 1)

      if (idx < 0)
        _hasNext = false
      else {
        // OPT: hand rolled version of `sum = nums.view(idx + 1, nums.length).sum + 1`
        var sum = 1
        var i = idx + 1
        while (i < nums.length) {
          sum += nums(i)
          i += 1
        }
        nums(idx) -= 1
        for (k <- (idx+1) until nums.length) {
          nums(k) = sum min cnts(k)
          sum -= nums(k)
        }
      }

      res
    }

    /** Rearrange seq to newSeq a0a0..a0a1..a1...ak..ak such that
     *  seq.count(_ == aj) == cnts(j)
     *
     *  @return     (newSeq,cnts,nums)
     */
    private def init(): (IndexedSeq[A], Array[Int], Array[Int]) = {
      val m = mutable.HashMap[A, Int]()

      // e => (e, weight(e))
      val (es, is) = (thisCollection map (e => (e, m.getOrElseUpdate(e, m.size))) sortBy (_._2)).unzip
      val cs = new Array[Int](m.size)
      is foreach (i => cs(i) += 1)

      val ns = new Array[Int](cs.length)

      var r = n
      0 until ns.length foreach { k =>
        ns(k) = r min cs(k)
        r -= ns(k)
      }
      (es.toIndexedSeq, cs, ns)
    }
  }


}




 }