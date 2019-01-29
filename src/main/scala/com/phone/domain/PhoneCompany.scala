package com.phone.domain

import com.phone.domain.CallPriceStrategies.DefaultCallPriceStrategy
import com.phone.domain.Promotions.MostCalledNumberDiscount

class PhoneCompany(allDailyCalls: Seq[PhoneCall],
                   callPriceStrategy: CallPriceStrategy = DefaultCallPriceStrategy,
                   promos: Seq[PromotionCalculator] = Seq(MostCalledNumberDiscount)) {


  def dailyCustomerCallReports(): Seq[DailyCustomerCallReport] = {
    allDailyCalls
    .groupBy(_.customerId)
    .map { case (customerId, calls) =>
      DailyCustomerCallReport(customerId,
                               callSubtotal(calls),
                               adjustForPromos(calls, promos, callPriceStrategy))
    }.toSeq
  }

  private def callSubtotal(calls: Seq[PhoneCall]) = {
    calls.map(c => callPriceStrategy(c)).sum
  }

  private def adjustForPromos(customerCalls: Seq[PhoneCall],
                              promos: Seq[PromotionCalculator],
                              callPriceStrategy: CallPriceStrategy): PromoAdjustment = {
    promos.map { p =>
      p(customerCalls, callPriceStrategy)
    }.sum
  }
}
