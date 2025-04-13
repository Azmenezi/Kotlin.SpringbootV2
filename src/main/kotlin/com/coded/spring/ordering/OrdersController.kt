package com.coded.spring.ordering

import org.springframework.web.bind.annotation.*

@RestController
class HelloWorldController(
    val ordersRepository: OrdersRepository,
    val itemsRepository: ItemsRepository
) {

    @GetMapping("/hello")
    fun helloWorld() = "Hello World"

    @PostMapping("/orders")
    fun submitOrder(@RequestBody request: OrderRequest) {
        val items = itemsRepository.findAllByNameIn(request.items)

        val order = OrderEntity(restaurant = request.restaurant, items = items)
        ordersRepository.save(order)
    }

    @PostMapping("/items")
    fun createItems(@RequestBody request: CreateItemsRequest): List<ItemsEntity> {
        val items = request.items.map { ItemsEntity(name = it) }
        return itemsRepository.saveAll(items)
    }




    @GetMapping("/orders")
    fun getOrders(): MutableList<OrderEntity> = ordersRepository.findAll()
}

data class OrderRequest(
    val restaurant: String,
    val items: List<String>
)

data class CreateItemsRequest(
    val items: List<String>
)
