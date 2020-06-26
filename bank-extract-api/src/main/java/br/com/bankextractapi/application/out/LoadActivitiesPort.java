package br.com.bankextractapi.application.out;

import br.com.bankextractapi.domain.Activity;

import java.util.List;

public interface LoadActivitiesPort {

    List<Activity> getActivitiesByAccount(Long accountId);
}
