package com.phone

import com.phone.domain.{DailyCustomerCallReport, PhoneCompany}
import com.phone.file.CallLogFileReader

object DailyCallReportGenerator {

  def apply(fileResource: String): Seq[DailyCustomerCallReport] = {
    val phoneCompany = new PhoneCompany(CallLogFileReader(fileResource))
    phoneCompany.dailyCustomerCallReports()
  }
}
