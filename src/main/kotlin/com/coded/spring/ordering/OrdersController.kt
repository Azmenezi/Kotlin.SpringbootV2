package com.coded.spring.ordering

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(){

    @GetMapping("/hello")
    fun helloWorld() =  "Hello World"
}