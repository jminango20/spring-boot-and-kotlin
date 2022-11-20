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

    fun findAll() : List<Person> {
        logger.info("Finding all people!")

        val listPerson : MutableList<Person> = mutableListOf()

        for (i in 0 .. 7){
            val person = mockPerson(i)
            listPerson.add(person)
        }
        return  listPerson
    }

    fun createPerson(person : Person) = person

    fun updatePerson(person : Person) = person

    fun deletePerson(id : Long){  }

    private fun mockPerson(i: Int): Person {
        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "First name $i"
        person.lastName = "Last name $i"
        person.address = "Some Address $i, Brazil"
        person.gender = "Male"
        return person
    }

}