
package com.coded.spring.ordering


import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface ItemsRepository : JpaRepository<ItemsEntity, Long> {
    fun findAllByNameIn(names: List<String>): List<ItemsEntity>
}


@Entity
@Table(name = "items")
data class ItemsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val name: String,

) {
    constructor() : this(null, "")
}
