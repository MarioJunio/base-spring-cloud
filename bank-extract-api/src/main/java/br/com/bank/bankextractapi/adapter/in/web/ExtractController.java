package br.com.bank.bankextractapi.adapter.in.web;

import br.com.bank.bankextractapi.application.in.IExtractUseCase;
import br.com.bank.bankextractapi.domain.Extract;
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
