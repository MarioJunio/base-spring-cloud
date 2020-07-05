package br.com.bank.bank.adapter.out.persistence.repository

import br.com.bank.bank.adapter.out.persistence.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<AccountEntity, Long> {
}