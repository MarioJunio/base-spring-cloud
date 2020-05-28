package br.com.bank.bankextractapi.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Extract {

    private List<Activity> activities = new ArrayList<>();
}
