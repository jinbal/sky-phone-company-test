package com.phone.domain

object Promotions {

  val MostCalledNumberDiscount: PromotionCalculator = (phoneCalls: Seq[PhoneCall], callPriceStrategy: CallPriceStrategy) => {
    val max: Map[PhoneNumber, Seq[PhoneCall]] = phoneCalls
                                                .groupBy(_.numberCalled)

    val d: Seq[Seq[PhoneCall]] = max.map(_._2).toSeq
    val f: Seq[PromoAdjustment] = d.map { calls =>
      calls.map { call =>
        callPriceStrategy(call)
      }.sum
    }
    val maxCallCostToSingleNmber = f.max

    maxCallCostToSingleNmber * -1
  }

}
