package com.phone.domain

import org.scalatest.{FunSuite, Matchers}

class PhoneCompanyTest extends FunSuite with Matchers {

  val StubCallPriceStrategy: (PhoneCall) => Int = (call: PhoneCall) => call.durationSeconds

  test("Should generate daily call report") {

  }

}
