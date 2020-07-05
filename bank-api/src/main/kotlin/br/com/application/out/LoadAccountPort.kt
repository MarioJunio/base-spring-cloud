package br.com.bank.bank.application.out

import br.com.bank.bank.domain.Account

interface LoadAccountPort {

    fun getAccount(id: Long): Account
}