package com.jminango.controller

import com.jminango.Greetings
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingsController {

    val index = AtomicLong()

    @RequestMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String?): Greetings {
        return Greetings(id = index.incrementAndGet(), content = "Hello, $name")
    }


}