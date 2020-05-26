package br.com.bank.application.`in`

import br.com.bank.domain.Activity

interface IQueryActivityUseCase {

    fun findActivitiesByAccount(accountId: Long): List<Activity>
}