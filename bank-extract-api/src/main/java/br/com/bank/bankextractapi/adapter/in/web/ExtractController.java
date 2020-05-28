package br.com.bank.bankextractapi.adapter.in.web;

import br.com.bank.bankextractapi.application.in.IExtractUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/extracts")
public class ExtractController {

    private IExtractUseCase extractService;

    @Autowired
    public ExtractController(IExtractUseCase extractService) {
        this.extractService = extractService;
    }

    @GetMapping("/account/{accountId}")
    public Mono<String> periodExtract(@PathVariable Long accountId) {

        extractService.extractByAccount(accountId);

        Mono<String> data = Mono.just("Called");

        return data;
    }
}
