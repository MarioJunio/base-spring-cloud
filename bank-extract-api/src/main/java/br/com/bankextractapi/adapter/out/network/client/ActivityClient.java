package br.com.bankextractapi.adapter.out.network.client;

import br.com.bankextractapi.adapter.in.web.dto.ActivityDto;
import br.com.bankextractapi.adapter.in.web.dto.ActivityDto;
import br.com.bankextractapi.adapter.in.web.dto.ActivityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "bank-api")
public interface ActivityClient {

    @GetMapping("/activities/{accountId}")
    List<ActivityDto> activitiesByAccount(@PathVariable Long accountId);

}
