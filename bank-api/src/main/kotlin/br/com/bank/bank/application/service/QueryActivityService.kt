package br.com.bank.bank.application.service

import br.com.bank.bank.application.`in`.IQueryActivityUseCase
import br.com.bank.bank.application.out.QueryActivityPort
import br.com.bank.bank.domain.Account
import br.com.bank.bank.domain.Activity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class QueryActivityService
@Autowired constructor(val queryActivityPort: QueryActivityPort) : IQueryActivityUseCase {

    override fun findActivitiesByAccount(accountId: Long): List<Activity> {
        return queryActivityPort.queryActivityByAccount(Account(accountId))
    }
}