package br.com.bank.bankextractapi.adapter.in.web;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/extracts")
public class ExtractController {

    @GetMapping("/period")
    public Mono<String> periodExtract(ServerHttpResponse response) {

        response.getHeaders().add("bank-extract-fail", "false");

        Mono<String> data = Mono.just("Resposta do serviço de extrato");

        return data;
    }
}
