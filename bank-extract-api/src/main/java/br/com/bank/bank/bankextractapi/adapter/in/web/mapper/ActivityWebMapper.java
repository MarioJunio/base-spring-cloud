package br.com.bank.bank.bankextractapi.adapter.in.web.mapper;

import br.com.bank.bank.bankextractapi.adapter.in.web.dto.ActivityDto;
import br.com.bank.bank.bankextractapi.domain.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityWebMapper {

    public Activity toDomain(ActivityDto activityDto) {
        return new Activity(activityDto.getId(), activityDto.getSourceAccount(), activityDto.getTargetAccount(), Activity.ActivityType.valueOf(activityDto.getType()), activityDto.getValue(), activityDto.getBalance(), activityDto.getDate());
    }
}
