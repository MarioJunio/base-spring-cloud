package br.com.bank.application.out

import br.com.bank.domain.Account
import br.com.bank.domain.Activity

interface UpdateAccountPort {

    fun save(account: Account): Account

    fun updateActivities(activities: MutableList<Activity>)
}