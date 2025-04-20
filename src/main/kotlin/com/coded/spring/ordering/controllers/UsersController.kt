package com.coded.spring.ordering.controllers

import com.coded.spring.ordering.services.UsersService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(
    private val usersService: UsersService
){

    @GetMapping("/users/v1/list")
    fun users() = usersService.listUsers()
}