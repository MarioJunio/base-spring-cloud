package br.com.bank.adapter.`in`.web.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class ActivityDto(val id: Long,
                       val sourceAccount: String?,
                       val targetAccount: String?,
                       val type: String,
                       val value: BigDecimal,
                       val balance: BigDecimal,
                       val date: LocalDateTime)