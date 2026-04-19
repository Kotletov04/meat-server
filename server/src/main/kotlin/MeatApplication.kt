package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
@ComponentScan("controllers", "usecases", "com.example")
class MeatApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<MeatApplication>(*args)
        }
    }
}

//fun main(args: Array<String>) {
//    runApplication<MeatApplication>(*args)
//}