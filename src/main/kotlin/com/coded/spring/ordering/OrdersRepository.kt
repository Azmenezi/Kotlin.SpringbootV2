package com.coded.spring.ordering


import jakarta.inject.Named
import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface OrdersRepository : JpaRepository<OrderEntity, Long>


@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val restaurant: String,
    val items: List<String>
) {
    constructor() : this(null, "", listOf(""))
}
