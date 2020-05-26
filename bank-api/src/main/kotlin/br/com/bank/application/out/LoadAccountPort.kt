package br.com.bank.application.out

import br.com.bank.domain.Account

interface LoadAccountPort {

    fun getAccount(id: Long): Account
}