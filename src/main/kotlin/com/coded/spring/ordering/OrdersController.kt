package com.coded.spring.ordering

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(
    val ordersRepository: OrdersRepository
) {

    @GetMapping("/hello")
    fun helloWorld() = "Hello World"

    @PostMapping("/orders")
    fun submitOrder(@RequestBody request: OrderRequest) {
        ordersRepository.save(OrderEntity(restaurant = request.restaurant, items = request.items))
    }
    
    @GetMapping("/orders")
    fun getOrders(): MutableList<OrderEntity> = ordersRepository.findAll()
}

data class OrderRequest(
    val restaurant: String,
    val items: List<String>
)