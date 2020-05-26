package br.com.bank.domain

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.validation.constraints.Min

open class Activity(val id: Long?,
                    val ownerAccount: Account,
                    val sourceAccount: Account?,
                    val targetAccount: Account?,
                    val type: TypeActivity,

                    @Min(1)
                    val value: BigDecimal,
                    val balance: BigDecimal,
                    val date: LocalDateTime) {

    constructor(ownerAccount: Account,
                sourceAccount: Account?,
                targetAccount: Account?,
                type: TypeActivity,
                value: BigDecimal,
                balance: BigDecimal,
                date: LocalDateTime): this(null, ownerAccount, sourceAccount, targetAccount, type, value, balance, date)

    constructor(ownerAccount: Account,
                type: TypeActivity,
                value: BigDecimal,
                balance: BigDecimal,
                date: LocalDateTime): this(null, ownerAccount, null, null, type, value, balance, date)


}