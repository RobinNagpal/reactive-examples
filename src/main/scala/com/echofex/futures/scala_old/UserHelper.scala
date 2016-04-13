package com.echofex.futures.scala_old


import java.util
import java.util.concurrent.TimeUnit

import com.echofex.futures.scala_old.service.EmploymentService
import com.echofex.futures.scala_old.service.FinancialService
import com.echofex.futures.scala_old.service.MoneyTransferService
import com.echofex.futures.scala_old.service.UserService
import com.echofex.futures.scala_old.service.impl.EmploymentServiceImpl
import com.echofex.futures.scala_old.service.impl.FinancialServiceImpl
import com.echofex.futures.scala_old.service.impl.UserServiceImpl
import com.echofex.futures.scala_old.util.MoneyTransferServiceFactory
import com.echofex.model.BankDetails
import com.echofex.model.Employer
import com.echofex.model.User
import scala.collection.JavaConverters._

import scala.concurrent.Future
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
    val userHomeCurrency: Future[String] = userF.map(user => financialService.getCurrencyCodeForCountry(user.getCountryCode))

    employersF.map {
      _.asScala.map {
        emp => {
          val yearlyEmpEarning: Future[Double] = for {
            employerCurrency <- Future(financialService.getCurrencyCodeForCountry(emp.getCountryCode))
            currencyConv: Double <- financialService.getCurrencyConversion(employerCurrency, userHomeCurrency.toString)
            yearlyEarnings: Double <- employmentService.getYearlyEarningForUserWithEmployer(userId, emp.getId())
          } yield (currencyConv * yearlyEarnings)
          yearlyEmpEarning
        }
      }
    }
    return null
  }
}