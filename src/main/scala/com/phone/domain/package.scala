package com.phone

package object domain {

  type PhoneNumber = String
  type CustomerId = String
  type PromoAdjustment = Double

  type CallPriceStrategy = (PhoneCall) => Double
  type PromotionCalculator = (Seq[PhoneCall], CallPriceStrategy) => PromoAdjustment

  case class PhoneCall(customerId: CustomerId, numberCalled: PhoneNumber, durationSeconds: Int)

  case class DailyCustomerCallReport(customerId: CustomerId, callTotal: Double, promoAdjustment: PromoAdjustment) {
    def finalTotal = callTotal + promoAdjustment
  }

}
