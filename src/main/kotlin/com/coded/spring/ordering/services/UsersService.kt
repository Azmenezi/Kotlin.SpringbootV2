package com.coded.spring.ordering.services

import com.coded.spring.ordering.repositories.UsersRepository
import jakarta.inject.Named

@Named
class UsersService(
    private val usersRepository: UsersRepository,
) {

    fun listUsers(): List<UserResponse> = usersRepository.findAll().map {
        UserResponse(
            name = it.name,
            age = it.age
        )
    }
}

data class UserResponse(
    val name: String,
    val age: Int
)