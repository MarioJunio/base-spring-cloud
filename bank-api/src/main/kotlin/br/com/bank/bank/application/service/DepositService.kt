package br.com.bank.bank.application.service

import br.com.bank.bank.application.`in`.IDepositUseCase
import br.com.bank.bank.application.out.LoadAccountPort
import br.com.bank.bank.application.out.UpdateAccountPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class DepositService @Autowired constructor(
        val loadAccountPort: LoadAccountPort,
        val updateAccountPort: UpdateAccountPort
) : IDepositUseCase {

    override fun deposit(accountId: Long, value: BigDecimal) {
        val account = loadAccountPort.getAccount(accountId)

        // perform the deposit
        account.deposit(value)

        // persist account
        updateAccountPort.save(account)
    }

}