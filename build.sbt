lazy val phoneCompany = (project in file("."))
  .settings(Seq(name := "disco-test-phone-company",
                 version := "1.0",
                 scalaVersion := "2.12.3"
               )
           )
lazy val dependencies = Seq("com.github.tototoshi" %% "scala-csv" % "1.3.5")

lazy val testDependencies = Seq("org.scalatest" %% "scalatest" % "3.0.5",
                                 "org.scalacheck" %% "scalacheck" % "1.14.0"
                               ) map (_ % "test")

libraryDependencies ++= dependencies
libraryDependencies ++= testDependencies
