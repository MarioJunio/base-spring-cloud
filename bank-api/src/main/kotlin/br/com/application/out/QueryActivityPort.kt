package br.com.bank.bank.application.out

import br.com.bank.bank.domain.Account
import br.com.bank.bank.domain.Activity

interface QueryActivityPort {

    fun queryActivityByAccount(account: Account): List<Activity>
}