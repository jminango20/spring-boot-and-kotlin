package com.jminango.services

import com.jminango.models.Person
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {

    private val counter : AtomicLong = AtomicLong()

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id: Long) : Person {
        logger.info("Finding one person!")

        val person = Person()
        person.id = id
        person.firstName = "Juan"
        person.lastName = "Minango"
        person.address = "Campinas, SP, Brazil"
        person.gender = "Male"
        return person
    }

}