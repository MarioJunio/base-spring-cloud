package br.com.bank.adapter.out.persistence.entity

import br.com.bank.domain.TypeActivity
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "activity")
data class ActivityEntity(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,

        @ManyToOne
        @JoinColumn(name = "owner_account")
        var account: AccountEntity,

        @ManyToOne
        @JoinColumn(name = "source_account")
        var sourceAccount: AccountEntity?,

        @ManyToOne
        @JoinColumn(name = "target_account")
        var targetAccount: AccountEntity?,

        @Enumerated(EnumType.STRING)
        @Column(name = "type")
        var type: TypeActivity,

        @Column
        var value: BigDecimal,

        @Column
        val balance: BigDecimal,

        @Column
        var date: LocalDateTime

) : Serializable