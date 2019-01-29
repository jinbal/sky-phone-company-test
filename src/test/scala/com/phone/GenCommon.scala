package com.phone

import com.phone.domain.{CustomerId, PhoneCall}
import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks

trait GenCommon extends GeneratorDrivenPropertyChecks {

  val genCustomerId: Gen[CustomerId] = Gen.alphaStr.suchThat(s => !s.isEmpty)
  val genNumberDialled: Gen[String] = Gen.alphaStr.suchThat(s => !s.isEmpty)
  val genDuration: Gen[Int] = Gen.chooseNum(10, 50000)

  def genPhoneCall(customerId: Gen[CustomerId] = genCustomerId,
                   numberDialled: Gen[String] = genNumberDialled,
                   duration: Gen[Int] = genDuration): Gen[PhoneCall] = for {
    customerId <- customerId
    numberDialled <- numberDialled
    duration <- duration
  } yield PhoneCall(customerId, numberDialled, duration)


  val genUniquePhoneCalls: Gen[Seq[PhoneCall]] = for {
    num <- Gen.chooseNum(10, 50)
    calls <- Gen.listOfN(num, genPhoneCall())
  } yield calls

  val genSingleAccountPhoneCalls: Gen[Seq[PhoneCall]] = for {
    num <- Gen.chooseNum(10, 50)
    customerId <- genCustomerId
    calls <- Gen.listOfN(num, genPhoneCall(customerId = customerId))
  } yield calls

}
