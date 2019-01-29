package com.phone.domain

import com.phone.GenCommon
import org.scalatest.{FunSuite, Inspectors, Matchers}

class PhoneCompanyTest extends FunSuite with Matchers with GenCommon {

  val Mock1PencePerCallPriceStrategy: CallPriceStrategy = (_) => 1

  val Mock100PercentDiscountPromo: PromotionCalculator = (calls: Seq[PhoneCall], pricing: CallPriceStrategy) => {
    (calls.map(c => pricing(c)).sum) * -1
  }


  test("Should generate daily call report with single call per account") {
    // Given
    forAll(genUniquePhoneCalls) { phoneCalls =>
      val phoneCompany = new PhoneCompany(phoneCalls, Mock1PencePerCallPriceStrategy, Seq.empty)
      // When
      val customerCallReports: Seq[DailyCustomerCallReport] = phoneCompany.dailyCustomerCallReports()

      // Then
      customerCallReports.size shouldBe phoneCalls.size
      Inspectors.forAll(customerCallReports) { report =>
        report.callTotal shouldBe 1
        report.promoAdjustment shouldBe 0
        report.finalTotal shouldBe 1
      }
    }
  }

  test("should generate correct daily report with multiple calls per account") {
    // Given
    forAll(genSingleAccountPhoneCalls) { phoneCalls =>
      val phoneCompany = new PhoneCompany(phoneCalls, Mock1PencePerCallPriceStrategy, Seq.empty)

      // When
      val customerCallReports: Seq[DailyCustomerCallReport] = phoneCompany.dailyCustomerCallReports()

      // Then
      customerCallReports.size shouldBe 1
      val report = customerCallReports.head
      report.callTotal shouldBe phoneCalls.size // 1 pence per call as per mock strategy
      report.promoAdjustment shouldBe 0
      report.finalTotal shouldBe phoneCalls.size
    }
  }

  test("should apply promo to daily call report") {
    // Given
    forAll(genUniquePhoneCalls) { phoneCalls =>
      val phoneCompany = new PhoneCompany(phoneCalls, Mock1PencePerCallPriceStrategy, Seq(Mock100PercentDiscountPromo))
      // When
      val customerCallReports: Seq[DailyCustomerCallReport] = phoneCompany.dailyCustomerCallReports()

      // Then
      customerCallReports.size shouldBe phoneCalls.size
      Inspectors.forAll(customerCallReports) { report =>
        report.callTotal shouldBe 1
        report.promoAdjustment shouldBe -1
        report.finalTotal shouldBe 0
      }
    }
  }

}
