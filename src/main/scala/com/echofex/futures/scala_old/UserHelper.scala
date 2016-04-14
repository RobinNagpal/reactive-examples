package com.echofex.futures.scala_old


import java.util.concurrent.TimeUnit

import com.echofex.futures.scala_old.service.MoneyTransferService
import com.echofex.futures.scala_old.service.impl.EmploymentServiceImpl
import com.echofex.futures.scala_old.service.impl.FinancialServiceImpl
import com.echofex.futures.scala_old.service.impl.UserServiceImpl
import com.echofex.futures.scala_old.util.MoneyTransferServiceFactory
import com.echofex.model.BankDetails
import com.echofex.model.Employer
import com.echofex.model.User
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

/**
  * Created by robin on 3/6/16.
  */
class UserHelper {


  val userService = new UserServiceImpl()
  val employmentService = new EmploymentServiceImpl()
  val financialService = new FinancialServiceImpl()

  @throws(classOf[InterruptedException])
  def transferYearlyEarningsToUsersAccount(userId: Long): Double = {


    val userIdF: Future[Long] = Future.successful(userId)
    val userF: Future[User] = Future(userService.getUserForId(userId))

    val employersF: Future[java.util.List[Employer]] = Future(employmentService.findEmployersForUserInYear(userId, "2015"))
    val userHomeCurrencyF: Future[String] = userF.map(user => financialService.getCurrencyCodeForCountry(user.getCountryCode))

    val employerYearlyEarningsF: Future[mutable.Buffer[Future[Double]]] = employersF.map {
      _.asScala.map {
        emp => {
          val employerCurrencyF = Future(financialService.getCurrencyCodeForCountry(emp.getCountryCode))
          val yearlyEarningsF = Future(employmentService.getYearlyEarningForUserWithEmployer(userId, emp.getId()))
          val yearlyEmpEarning: Future[Double] = for {
            employerCurrency <- employerCurrencyF
            userHomeCurrency <- userHomeCurrencyF
            currencyConv <- Future(financialService.getCurrencyConversion(employerCurrency, userHomeCurrency))
            yearlyEarnings <- yearlyEarningsF
          } yield (currencyConv * yearlyEarnings)
          yearlyEmpEarning
        }
      }
    }

    val bankDetailsF: Future[BankDetails] = userIdF.map(financialService.getBankDetailsForUser)
    val moneyTransferServiceF: Future[MoneyTransferService] = userHomeCurrencyF.map(MoneyTransferServiceFactory.getMoneyTransferServiceForCurrency)
    val employerEarningsF: Future[mutable.Buffer[Double]] = employerYearlyEarningsF.flatMap(Future.sequence(_))
    val totalEarningsF: Future[Double] = employerEarningsF.map(_.sum)

    val moneyThatWasTransferred: Future[Double] = for {
      bankDetails <- bankDetailsF
      moneyTransferService <- moneyTransferServiceF
      totalEarnings <- totalEarningsF
      transferredMoney <- Future(moneyTransferService.transferMoneyToAccount(bankDetails.getBankName, bankDetails.getAccountNumber, totalEarnings))
    } yield transferredMoney


    return Await.result(moneyThatWasTransferred, Duration(20, TimeUnit.SECONDS))
  }
}