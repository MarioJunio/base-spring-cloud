package br.com.bank.adapter.out.persistence.repository

import br.com.bank.adapter.out.persistence.entity.AccountEntity
import br.com.bank.adapter.out.persistence.entity.ActivityEntity
import br.com.bank.domain.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ActivityRepository : JpaRepository<ActivityEntity, Long> {

    fun findByAccount(account: AccountEntity): List<ActivityEntity>
}