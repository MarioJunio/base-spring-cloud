package br.com.bank.bank.bankextractapi.adapter.in.web.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ActivityDto {

    private Long id;
    private String sourceAccount;
    private String targetAccount;
    private String type;
    private BigDecimal value;
    private BigDecimal balance;
    private LocalDateTime date;

}
