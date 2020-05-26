package br.com.bank.adapter.out.persistence.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

data class AccountNotFound(override val message: String) : ResponseStatusException(HttpStatus.NOT_FOUND, message);