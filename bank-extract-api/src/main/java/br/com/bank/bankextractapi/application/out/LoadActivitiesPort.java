package br.com.bank.bankextractapi.application.out;

import br.com.bank.bankextractapi.domain.Activity;

import java.util.List;

public interface LoadActivitiesPort {

    List<Activity> getActivitiesByAccount(Long accountId);
}
