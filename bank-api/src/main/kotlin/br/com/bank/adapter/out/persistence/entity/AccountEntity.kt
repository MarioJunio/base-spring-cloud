package br.com.bank.adapter.out.persistence.entity

import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "account")
class AccountEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column
    var name: String = ""

    @Column
    var balance: BigDecimal = BigDecimal.ZERO

    @OneToMany(mappedBy = "account", cascade = [CascadeType.ALL])
    var ownerActivities: MutableList<ActivityEntity> = mutableListOf()

    constructor(id: Long, name: String, balance: BigDecimal, ownerActivities: MutableList<ActivityEntity>) {
        this.id = id
        this.name = name
        this.balance = balance
        this.ownerActivities = ownerActivities
    }

    constructor(id: Long) {
        this.id = id
    }
}