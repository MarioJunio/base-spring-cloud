package br.com.bank.adapter.`in`.web.mapper

import br.com.bank.adapter.`in`.web.dto.ActivityDto
import br.com.bank.domain.Activity
import org.springframework.stereotype.Component

@Component
class ActivityWebMapper {

    fun toDto(activity: Activity): ActivityDto = ActivityDto(activity.id!!, activity.sourceAccount?.name, activity.targetAccount?.name, activity.type.name, activity.value, activity.balance, activity.date)
}