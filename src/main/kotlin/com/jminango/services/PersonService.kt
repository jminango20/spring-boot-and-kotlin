package com.jminango.services

import com.jminango.exceptions.ResourceNotFoundException
import com.jminango.models.Person
import com.jminango.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var personRepository : PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll() : List<Person> {
        logger.info("Finding all people!")
        return personRepository.findAll()
    }

    fun findById(id: Long) : Person {
        logger.info("Finding person! with id $id")
        return personRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this Id $id") }
    }

    fun createPerson(person : Person) : Person {
        logger.info("Create a person with name ${person.firstName}!")
        return personRepository.save(person)
    }

    fun updatePerson(person: Person) : Person {
        logger.info("Updating a person with Id ${person.id}")

        val entity = personRepository.findById(person.id)
            .orElseThrow{ResourceNotFoundException("No records found for this Id")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return personRepository.save(entity)
    }
    fun deletePerson(id : Long) {
        logger.info("Delete a person with id ${id} !")

        val entity = personRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this Id ${id}") }

        personRepository.delete(entity)

    }



}