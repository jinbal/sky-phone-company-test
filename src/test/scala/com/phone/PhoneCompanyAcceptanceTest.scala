package com.phone

import com.phone.domain.PhoneCompany
import com.phone.file.CallLogFileReader
import org.scalatest.OptionValues._
import org.scalatest.{FunSuite, Matchers}

class PhoneCompanyAcceptanceTest extends FunSuite with Matchers with GenCommon {

  /**
    * See calls-manual-solution.log for assertion values used in this test
    */
  test("Should correctly parse supplied call.log and generate call reports") {
    // Given
    val callLogResource = "calls.log"
    val phoneCompany = new PhoneCompany(CallLogFileReader(callLogResource))

    // When
    val reports = phoneCompany.dailyCustomerCallReports()

    // Then
    reports.size shouldBe 2
    val accountA = reports.find(_.customerId == "A").value
    accountA.callTotal shouldBe 52.67 +- 0.01
    accountA.promoAdjustment shouldBe -21.29 +- 0.01
    accountA.finalTotal shouldBe 31.38 +- 0.01

    val accountB = reports.find(_.customerId == "B").value
    accountB.callTotal shouldBe 51.96 +- 0.01
    accountB.promoAdjustment shouldBe -21.88 +- 0.01
    accountB.finalTotal shouldBe 30.08 +- 0.01

    println(reports.toString())
  }
}
