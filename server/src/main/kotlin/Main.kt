package com.example

import controllers.AuthController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
@ComponentScan("controllers", "usecases", "com.example")
class MyApplication

fun main(args: Array<String>) {
    runApplication<MyApplication>(*args)
}