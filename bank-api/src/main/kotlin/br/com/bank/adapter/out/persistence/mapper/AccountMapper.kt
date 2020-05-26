package br.com.bank.adapter.out.persistence.mapper

import br.com.bank.adapter.out.persistence.entity.AccountEntity
import br.com.bank.adapter.out.persistence.entity.ActivityEntity
import br.com.bank.domain.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AccountMapper {

    var activityMapper: ActivityMapper

    @Autowired
    constructor(activityMapper: ActivityMapper) {
        this.activityMapper = activityMapper
    }

    fun toDomain(accountEntity: AccountEntity): Account {
        return Account(accountEntity.id, accountEntity.name, accountEntity.balance)
    }

    fun toEntity(account: Account): AccountEntity {

        val activities: MutableList<ActivityEntity> = account.activities.map {
            activityMapper.toEntity(it)
        }.toMutableList()

        return AccountEntity(account.id, account.name, account.balance, activities)
    }
}