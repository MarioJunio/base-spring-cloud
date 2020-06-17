package br.com.bank.bank.bankextractapi.application.out;

import br.com.bank.bank.bankextractapi.domain.Activity;

import java.util.List;

public interface LoadActivitiesPort {

    List<Activity> getActivitiesByAccount(Long accountId);
}
