package com.phone.domain

import com.phone.GenCommon
import org.scalacheck.Gen
import org.scalatest.{FunSuite, Matchers}

class PromotionsTest extends FunSuite with Matchers with GenCommon {

  val Mock1PencePerCallPriceStrategy: CallPriceStrategy = (_) => 1

  test("Should generate correct adjustment for calls to number with highest value") {
    // Given
    val customerId = Gen.const("A")
    val largestList = Gen.listOfN(10, genPhoneCall(customerId = customerId, numberDialled = Gen.const("123"))).sample.get
    val smallestList = Gen.listOfN(5, genPhoneCall(customerId = customerId, numberDialled = Gen.const("234"))).sample.get

    //When
    val adjustment = Promotions.MostCalledNumberDiscount(largestList ++ smallestList, Mock1PencePerCallPriceStrategy)

    // Then
    adjustment shouldBe -10
  }

}
