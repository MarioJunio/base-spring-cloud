package br.com.bank.domain

import java.math.BigDecimal
import java.time.LocalDateTime

class Account(val id: Long,
              val name: String,
              val balance: BigDecimal,
              val activities: MutableList<Activity> = mutableListOf()) {

    constructor(id: Long,
                name: String,
                balance: BigDecimal) : this(id, name, balance, mutableListOf<Activity>())

    constructor(id: Long) : this(id, "", BigDecimal.ZERO)

    fun deposit(value: BigDecimal) {

        // creates new activity for deposit
        val activity = Activity(this, TypeActivity.DEPOSIT, value, calcBalance(), LocalDateTime.now())

        this.activities.add(activity);
    }

    fun calcBalance(): BigDecimal {

        val depositSum: BigDecimal = this.activities.filter { it.type == TypeActivity.DEPOSIT }
                .map { it.value }
                .fold(BigDecimal.ZERO) { sum, value: BigDecimal -> sum.add(value) }

        val withdrawSum: BigDecimal = this.activities.filter { it.type == TypeActivity.WITHDRAW }
                .map { it.value }
                .fold(BigDecimal.ZERO) { withdrawSum, value: BigDecimal -> withdrawSum.add(value) }

        return this.balance.add(depositSum).subtract(withdrawSum);
    }
}