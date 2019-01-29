package com.phone.domain

import com.phone.GenCommon
import com.phone.domain.CallPriceStrategies.DefaultCallPriceStrategy
import org.scalacheck.Gen
import org.scalatest.{FunSuite, Matchers}

class CallPriceStrategiesTest extends FunSuite with Matchers with GenCommon {

  test("should correctly calculate call cost for < 3 minute call") {
    // Given
    val duration = Gen.const(10)
    val call = genPhoneCall(duration = duration).sample.get

    // When
    val cost = DefaultCallPriceStrategy(call)

    // Then
    cost shouldBe 0.5
  }

  test("should correctly calculate cost for exactly 3 min call") {
    // Given
    val duration = Gen.const(180)

    val call = genPhoneCall(duration = duration).sample.get

    // When
    val cost = DefaultCallPriceStrategy(call)

    // Then
    cost shouldBe 9
  }

  test("should correctly calculate cost for longer than 3 min call") {
    // Given
    val duration = Gen.const(200)

    val call = genPhoneCall(duration = duration).sample.get

    // When
    val cost = DefaultCallPriceStrategy(call)

    // Then
    cost shouldBe (9 + 0.6)
  }

}
