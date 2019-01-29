package com.phone

object Main extends App {

  override def main(args: Array[String]): Unit = {
    val callLog = args(0)
    DailyCallReportPrinter(callLog)
  }

}
