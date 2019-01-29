package com.phone.file

import java.io.InputStreamReader

import com.github.tototoshi.csv.{CSVReader, DefaultCSVFormat}
import com.phone.domain
import com.phone.domain.PhoneCall

/**
  * @todo make more defensive with field validation etc
  */
object CallLogFileReader {
  implicit val spaceDelimitedConfig = new DefaultCSVFormat {
    override val delimiter: Char = ' '
    override val treatEmptyLineAsNil: Boolean = true
  }

  def apply(resource: String): Seq[domain.PhoneCall] = {
    val in = CSVReader.open(new InputStreamReader(getClass.getClassLoader.getResourceAsStream(resource), "UTF8"))
    in.iterator.flatMap { line =>
      line match {
        case Seq(id, number, dur) => Option(PhoneCall(id, number, parseDuration(dur)))
        case _ => None
      }
    }.toSeq
  }


  private def parseDuration(str: String) = {
    val durationParts = str.split(":")
    val hoursSecs = durationParts(0).toInt * 3600
    val minSecs = durationParts(1).toInt * 60
    val secs = durationParts(2).toInt
    hoursSecs + minSecs + secs
  }

}
