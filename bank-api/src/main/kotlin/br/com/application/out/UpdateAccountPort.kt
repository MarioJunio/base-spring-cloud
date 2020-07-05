package br.com.bank.bank.application.out

import br.com.bank.bank.domain.Account
import br.com.bank.bank.domain.Activity

interface UpdateAccountPort {

    fun save(account: Account): Account

    fun updateActivities(activities: MutableList<Activity>)
}