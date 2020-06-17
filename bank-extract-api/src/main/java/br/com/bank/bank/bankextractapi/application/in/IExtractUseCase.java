package br.com.bank.bank.bankextractapi.application.in;

import br.com.bank.bank.bankextractapi.domain.Extract;

public interface IExtractUseCase {

    Extract extractByAccount(Long accountId);
}
