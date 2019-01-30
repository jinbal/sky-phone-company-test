package com.phone.domain

object CallPriceStrategies {

  val DefaultCallPriceStrategy: CallPriceStrategy = (call: PhoneCall) => {
    val primaryPerSecondPrice = 0.05
    val secondaryPerSecondPrice = 0.03
    val duration = call.durationSeconds
    val threeMins = 180
    if (duration <= threeMins) {
      duration * primaryPerSecondPrice
    } else {
      val rem = duration - threeMins
      (rem * secondaryPerSecondPrice) + (threeMins * primaryPerSecondPrice)
    }
  }
}
