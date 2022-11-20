package com.jminango.controller

import com.jminango.models.Person
import com.jminango.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private lateinit var personService : PersonService

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable(value = "id") id : Long) : Person {
        return personService.findById(id)
    }

    @RequestMapping(method = [RequestMethod.GET], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll() : List<Person>{
        return personService.findAll()
    }

    @RequestMapping(method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createPerson(@RequestBody person: Person) : Person {
        return personService.createPerson(person)
    }

    @RequestMapping(method = [RequestMethod.PUT], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updatePerson(@RequestBody person: Person) : Person {
        return personService.updatePerson(person)
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deletePerson(@PathVariable(value = "id") id : Long) {
        personService.deletePerson(id)
    }


}