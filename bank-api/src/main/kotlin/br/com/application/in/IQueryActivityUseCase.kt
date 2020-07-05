package br.com.bank.bank.application.`in`

import br.com.bank.bank.domain.Activity

interface IQueryActivityUseCase {

    fun findActivitiesByAccount(accountId: Long): List<Activity>
}