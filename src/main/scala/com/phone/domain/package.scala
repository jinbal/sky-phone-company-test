package com.phone

package object domain {

  type PhoneNumber = String
  type CustomerId = String

  type CallPriceStrategy = (PhoneCall) => Int
  type PromotionCalculator = (Seq[PhoneCall]) => Int

  case class PhoneCall(customerId: CustomerId, numberCalled: PhoneNumber, durationSeconds: Int)

  case class DailyCustomerCallReport(customerId: CustomerId, callTotal: Int, promoAdjustment: Int, finalTotal: Int)

}
