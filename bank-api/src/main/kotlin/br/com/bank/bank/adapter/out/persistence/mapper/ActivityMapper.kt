package br.com.bank.bank.adapter.out.persistence.mapper

import br.com.bank.bank.adapter.out.persistence.entity.AccountEntity
import br.com.bank.bank.adapter.out.persistence.entity.ActivityEntity
import br.com.bank.bank.domain.Account
import br.com.bank.bank.domain.Activity
import org.springframework.stereotype.Component

@Component
class ActivityMapper {

    fun toEntity(activity: Activity): ActivityEntity {
        val ownerAccount = AccountEntity(activity.ownerAccount.id)
        val sourceAccount = if (activity.sourceAccount != null) AccountEntity(activity.sourceAccount.id) else null
        val targetAccount = if (activity.targetAccount != null) AccountEntity(activity.targetAccount.id) else null

        return ActivityEntity(activity.id, ownerAccount, sourceAccount, targetAccount, activity.type, activity.value, activity.balance, activity.date)
    }

    fun toDomain(activityEntity: ActivityEntity): Activity {
        val ownerAccount = Account(activityEntity.account.id)
        val sourceAccount = if (activityEntity.sourceAccount != null) Account(activityEntity.sourceAccount!!.id) else null
        val targetAccount = if (activityEntity.targetAccount != null) Account(activityEntity.targetAccount!!.id) else null

        return Activity(activityEntity.id, ownerAccount, sourceAccount, targetAccount, activityEntity.type, activityEntity.value, activityEntity.balance, activityEntity.date)
    }
}