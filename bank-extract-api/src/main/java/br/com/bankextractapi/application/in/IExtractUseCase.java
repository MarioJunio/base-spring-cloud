package br.com.bankextractapi.application.in;

import br.com.bankextractapi.domain.Extract;

public interface IExtractUseCase {

    Extract extractByAccount(Long accountId);
}
