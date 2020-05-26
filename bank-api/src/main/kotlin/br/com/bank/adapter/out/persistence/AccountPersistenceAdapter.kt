package br.com.bank.adapter.out.persistence

import br.com.bank.adapter.out.persistence.entity.AccountEntity
import br.com.bank.adapter.out.persistence.exception.AccountNotFound
import br.com.bank.adapter.out.persistence.mapper.AccountMapper
import br.com.bank.adapter.out.persistence.mapper.ActivityMapper
import br.com.bank.adapter.out.persistence.repository.AccountRepository
import br.com.bank.adapter.out.persistence.repository.ActivityRepository
import br.com.bank.application.out.LoadAccountPort
import br.com.bank.application.out.UpdateAccountPort
import br.com.bank.domain.Account
import br.com.bank.domain.Activity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.transaction.Transactional

@Component
@Transactional
class AccountPersistenceAdapter @Autowired constructor(
        val accountRepository: AccountRepository,
        val activityRepository: ActivityRepository,
        val accountMapper: AccountMapper,
        val activityMapper: ActivityMapper) : LoadAccountPort, UpdateAccountPort {

    override fun getAccount(id: Long): Account {
        val accountEntity: AccountEntity = accountRepository.findById(id).orElseThrow { AccountNotFound("Account not found!") }

        return accountMapper.toDomain(accountEntity);
    }

    override fun save(account: Account): Account {
        val accountEntity = accountMapper.toEntity(account)

        val accountSaved = accountRepository.save(accountEntity)

        return accountMapper.toDomain(accountSaved)
    }

    override fun updateActivities(activities: MutableList<Activity>) {
        activities.forEach {
            activityRepository.save(activityMapper.toEntity(it))
        }
    }

}