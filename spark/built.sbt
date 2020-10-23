name := "PalindromesSearchSpark"

version := "1.0"

scalaVersion := "2.12.12"

// libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "0.2.0" needed for scala >= 2.13 

val sparkVersion = "3.0.1"

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
      "org.apache.spark" %% "spark-sql" % sparkVersion

)