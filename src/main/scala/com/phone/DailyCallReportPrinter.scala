package com.phone

import com.phone.domain.PhoneCompany
import com.phone.file.CallLogFileReader

object DailyCallReportPrinter {

  def apply(fileResource: String) = {
    val phoneCompany = new PhoneCompany(CallLogFileReader(fileResource))
    phoneCompany.dailyCustomerCallReports().foreach { report =>
      println(s"CustomerId - ${report.customerId}")
      println(s"Call Total - ${report.callTotal}")
      println(s"Promo adjustment - ${report.promoAdjustment}")
      println(s"Final Total - ${report.finalTotal}")
      println()
    }
  }
}
