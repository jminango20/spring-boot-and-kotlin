package com.jminango

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootAndKotlinApplication

fun main(args: Array<String>) {
	runApplication<SpringBootAndKotlinApplication>(*args)
}
