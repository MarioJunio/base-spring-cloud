package br.com.bankextractapi.adapter.out.network;

import br.com.bankextractapi.adapter.out.network.client.ActivityClient;
import br.com.bankextractapi.application.out.LoadActivitiesPort;
import br.com.bankextractapi.adapter.out.network.client.ActivityClient;
import br.com.bankextractapi.application.out.LoadActivitiesPort;
import br.com.bankextractapi.adapter.out.network.client.ActivityClient;
import br.com.bankextractapi.application.out.LoadActivitiesPort;
import br.com.bankextractapi.adapter.in.web.dto.ActivityDto;
import br.com.bankextractapi.adapter.in.web.mapper.ActivityWebMapper;
import br.com.bankextractapi.adapter.out.network.client.ActivityClient;
import br.com.bankextractapi.application.out.LoadActivitiesPort;
import br.com.bankextractapi.domain.Activity;
import br.com.bankextractapi.adapter.out.network.client.ActivityClient;
import br.com.bankextractapi.application.out.LoadActivitiesPort;
import br.com.bankextractapi.adapter.out.network.client.ActivityClient;
import br.com.bankextractapi.application.out.LoadActivitiesPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivityNetworkAdapter implements LoadActivitiesPort {

    private RestTemplate restTemplate;
    private ActivityWebMapper activityWebMapper;
    private ActivityClient activityClient;

    @Autowired
    public ActivityNetworkAdapter(RestTemplate restTemplate, ActivityWebMapper activityWebMapper, ActivityClient activityClient) {
        this.restTemplate = restTemplate;
        this.activityWebMapper = activityWebMapper;
        this.activityClient = activityClient;
    }

    @Override
    public List<Activity> getActivitiesByAccount(Long accountId) {
        List<ActivityDto> activityDtos = activityClient.activitiesByAccount(accountId);

        /*ResponseEntity<List<ActivityDto>> activitiesDto = restTemplate
                .exchange(String.format("http://bank-api/activities/%d", accountId),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<ActivityDto>>() {
                        });*/
        return activityDtos.stream().map(activityDto -> activityWebMapper.toDomain(activityDto)).collect(Collectors.toList());
//        return activitiesDto.getBody().stream().map(activityDto -> activityWebMapper.toDomain(activityDto)).collect(Collectors.toList());
    }
}
