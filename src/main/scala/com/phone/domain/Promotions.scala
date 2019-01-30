package com.phone.domain

object Promotions {

  val MostCalledNumberDiscount: PromotionCalculator = (phoneCalls: Seq[PhoneCall], callPriceStrategy: CallPriceStrategy) => {
    phoneCalls
    .groupBy(_.numberCalled)
    .map(_._2)
    .toSeq
    .map { calls =>
      calls.map { call =>
        callPriceStrategy(call)
      }.sum
    }.max * -1
  }
}
