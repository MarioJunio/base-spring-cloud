package br.com.bankextractapi.application.service;

import br.com.bankextractapi.adapter.out.network.ActivityNetworkAdapter;
import br.com.bankextractapi.application.in.IExtractUseCase;
import br.com.bankextractapi.domain.Activity;
import br.com.bankextractapi.domain.Extract;
import br.com.bankextractapi.adapter.out.network.ActivityNetworkAdapter;
import br.com.bankextractapi.application.in.IExtractUseCase;
import br.com.bankextractapi.adapter.out.network.ActivityNetworkAdapter;
import br.com.bankextractapi.application.in.IExtractUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Service
public class ExtractService implements IExtractUseCase {

    private ActivityNetworkAdapter activityNetworkAdapter;

    @Autowired
    public ExtractService(ActivityNetworkAdapter activityNetworkAdapter) {
        this.activityNetworkAdapter = activityNetworkAdapter;
    }

    @Override
    public Extract extractByAccount(Long accountId) {
        List<Activity> activities = this.activityNetworkAdapter.getActivitiesByAccount(accountId);
        activities.sort(Comparator.comparing(Activity::getDate));

        BigDecimal lastBalance = activities.stream().findFirst().orElseGet(() -> new Activity(BigDecimal.ZERO)).getBalance();

        for (Activity act : activities) {
            act.setBalance(lastBalance = calcBalance(act, lastBalance));
        }

        return new Extract(activities);
    }

    private BigDecimal calcBalance(Activity activity, BigDecimal lastBalance) {

        switch (activity.getType()) {

            case DEPOSIT:
                return lastBalance.add(activity.getValue());

            case WITHDRAW:
            case TRANSFER:
                return lastBalance.subtract(activity.getValue());

            default:
                return lastBalance;
        }
    }
}
