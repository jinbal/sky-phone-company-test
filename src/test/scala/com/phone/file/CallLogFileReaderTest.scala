package com.phone.file

import com.phone.domain
import org.scalatest.{FunSuite, Matchers}

class CallLogFileReaderTest extends FunSuite with Matchers {


  test("should parse call log file") {
    // Given
    val input = "CallLogFileReaderTest.log"

    // When
    val calls: Seq[domain.PhoneCall] = CallLogFileReader(input)

    // Then
    calls.size shouldBe 2
    val first = calls.head
    first.customerId shouldBe "A"
    first.numberCalled shouldBe "555-333-212"
    first.durationSeconds shouldBe 54123

    val second = calls.last
    second.customerId shouldBe "B"
    second.numberCalled shouldBe "555-433-242"
    second.durationSeconds shouldBe 18401

  }

}
