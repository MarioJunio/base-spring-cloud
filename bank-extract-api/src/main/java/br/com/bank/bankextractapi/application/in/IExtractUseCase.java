package br.com.bank.bankextractapi.application.in;

import br.com.bank.bankextractapi.domain.Extract;

public interface IExtractUseCase {

    Extract extractByAccount(Long accountId);
}
