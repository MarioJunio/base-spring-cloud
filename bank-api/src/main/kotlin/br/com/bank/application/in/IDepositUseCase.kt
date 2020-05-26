package br.com.bank.application.`in`

import java.math.BigDecimal

interface IDepositUseCase {

    fun deposit(accountId: Long, value: BigDecimal)
}