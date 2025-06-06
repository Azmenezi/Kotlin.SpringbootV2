package com.coded.spring.ordering.repositories

import com.coded.spring.ordering.entities.UserEntity
import jakarta.inject.Named
import org.springframework.data.jpa.repository.JpaRepository

@Named
interface UsersRepository : JpaRepository<UserEntity, Long> {
    fun age(age: Int): MutableList<UserEntity>

    fun findByUsername(username: String): UserEntity?

}



