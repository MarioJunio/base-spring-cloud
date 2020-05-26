package br.com.bank.adapter.out.persistence

import br.com.bank.adapter.out.persistence.mapper.AccountMapper
import br.com.bank.adapter.out.persistence.mapper.ActivityMapper
import br.com.bank.adapter.out.persistence.repository.ActivityRepository
import br.com.bank.application.out.QueryActivityPort
import br.com.bank.domain.Account
import br.com.bank.domain.Activity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
@Transactional
class ActivityPersistenceAdapter
@Autowired constructor(
        val activityRepository: ActivityRepository,
        val accountMapper: AccountMapper,
        val activityMapper: ActivityMapper) : QueryActivityPort {

    override fun queryActivityByAccount(account: Account): List<Activity> {
        return this.activityRepository.findByAccount(accountMapper.toEntity(account)).map { activityMapper.toDomain(it) }.toList()
    }
}