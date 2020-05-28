package br.com.bank.bankextractapi.application.service;

import br.com.bank.bankextractapi.adapter.out.network.ActivityNetworkAdapter;
import br.com.bank.bankextractapi.application.in.IExtractUseCase;
import br.com.bank.bankextractapi.domain.Extract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractService implements IExtractUseCase {

    private ActivityNetworkAdapter activityNetworkAdapter;

    @Autowired
    public ExtractService(ActivityNetworkAdapter activityNetworkAdapter) {
        this.activityNetworkAdapter = activityNetworkAdapter;
    }

    @Override
    public Extract extractByAccount(Long accountId) {
        this.activityNetworkAdapter.getActivitiesByAccount(accountId);
        return null;
    }
}
