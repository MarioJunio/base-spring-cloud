package br.com.bankextractapi.adapter.in.web;

import br.com.bankextractapi.application.in.IExtractUseCase;
import br.com.bankextractapi.domain.Extract;
import br.com.bankextractapi.application.in.IExtractUseCase;
import br.com.bankextractapi.domain.Extract;
import br.com.bankextractapi.application.in.IExtractUseCase;
import br.com.bankextractapi.domain.Extract;
import br.com.bankextractapi.application.in.IExtractUseCase;
import br.com.bankextractapi.domain.Extract;
import br.com.bankextractapi.application.in.IExtractUseCase;
import br.com.bankextractapi.domain.Extract;
import br.com.bankextractapi.application.in.IExtractUseCase;
import br.com.bankextractapi.domain.Extract;
import br.com.bankextractapi.application.in.IExtractUseCase;
import br.com.bankextractapi.application.in.IExtractUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/extracts")
public class ExtractController {

    private IExtractUseCase extractService;

    @Autowired
    public ExtractController(IExtractUseCase extractService) {
        this.extractService = extractService;
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<Extract> periodExtract(@PathVariable Long accountId) {
        Extract extract = extractService.extractByAccount(accountId);

        return ResponseEntity.ok(extract);
    }
}
