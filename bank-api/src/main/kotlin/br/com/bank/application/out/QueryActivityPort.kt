package br.com.bank.application.out

import br.com.bank.domain.Account
import br.com.bank.domain.Activity

interface QueryActivityPort {

    fun queryActivityByAccount(account: Account): List<Activity>
}