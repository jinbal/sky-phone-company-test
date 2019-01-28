package com.phone.domain

class CustomerAccount(customerId: CustomerId,
                      phoneCalls: Seq[PhoneCall],
                      callCostCalculator: CallPriceStrategy,
                      promos: Seq[PromotionCalculator]) {

  def dailyCustomerCallReport:DailyCustomerCallReport = ???

}
