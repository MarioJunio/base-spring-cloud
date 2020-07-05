package br.com.bank.bank.adapter.`in`.web

import br.com.bank.bank.adapter.`in`.web.dto.DepositDto
import br.com.bank.bank.application.`in`.IDepositUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountController @Autowired constructor(val depositUseCase: IDepositUseCase) {

    val logger: Logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    fun doDeposit(@RequestBody depositDto: DepositDto) {
        this.logger.info("=> $depositDto")

        depositUseCase.deposit(depositDto.accountId, depositDto.value)
    }

}