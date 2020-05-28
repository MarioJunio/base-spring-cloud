package br.com.bank.bankextractapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    public enum ActivityType {
        DEPOSIT, WITHDRAW, TRANSFER
    }

    private Long id;
    private String sourceAccount;
    private String targetAccount;
    private ActivityType type;
    private BigDecimal value;
    private BigDecimal balance;
    private LocalDateTime date;
}
