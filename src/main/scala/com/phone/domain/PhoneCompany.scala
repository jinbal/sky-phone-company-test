package com.phone.domain

import com.phone.domain.CallPriceStrategies.DefaultCallPriceStrategy

class PhoneCompany(allDailyCalls: Seq[PhoneCall],
                   callPriceStrategy: CallPriceStrategy = DefaultCallPriceStrategy,
                   promos: Seq[PromotionCalculator] = Seq.empty) {

  def generateDailyCallReports(): Seq[DailyCustomerCallReport] = ???
}
