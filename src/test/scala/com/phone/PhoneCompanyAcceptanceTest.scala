package com.phone

import org.scalatest.{FunSuite, Matchers}
import org.scalatest.OptionValues._

class PhoneCompanyAcceptanceTest extends FunSuite with Matchers with GenCommon {

  /**
    * See calls-manual-solution.log for assertion values
    */
  test("Should correctly parse supplied call.log and generate call reports") {
    // Given
    val callLogResource = "calls.log"

    // When
    val reports = DailyCallReportGenerator(callLogResource)

    // Then
    reports.size shouldBe 2
    val accountA = reports.find(_.customerId == "A").value
    accountA.callTotal shouldBe 52.67
    accountA.promoAdjustment shouldBe -21.29
    accountA.finalTotal shouldBe 31.38

    val accountB = reports.find(_.customerId == "B").value
    accountB.callTotal shouldBe 46.53
    accountB.promoAdjustment shouldBe -27.63
    accountB.finalTotal shouldBe 18.9

    reports.toString()
  }
}
