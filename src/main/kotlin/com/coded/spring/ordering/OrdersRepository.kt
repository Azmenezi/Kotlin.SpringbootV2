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

    @ManyToMany
    @JoinTable(
        name = "order_items", // This is the join table
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    val items: List<ItemsEntity> = emptyList()
)
 {
    constructor() : this(null, "", listOf(ItemsEntity()))
}
