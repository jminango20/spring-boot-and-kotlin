package com.jminango.controller

import com.jminango.data.vo.v1.PersonVO
import com.jminango.data.vo.v2.PersonVO as PersonVOV2
import com.jminango.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person/v1")
class PersonController {


    @Autowired
    private lateinit var personService : PersonService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"])
    fun findAll() : List<PersonVO>{
        return personService.findAll()
    }
    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"])
    fun findById(@PathVariable(value = "id") id : Long) : PersonVO {
        return personService.findById(id)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"], consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"])
    fun createPerson(@RequestBody person: PersonVO) : PersonVO {
        return personService.createPerson(person)

    }    @PostMapping(value = ["/v2"], produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"], consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"])
    fun createPersonV2(@RequestBody person: PersonVOV2) : PersonVOV2 {
        return personService.createPersonV2(person)
    }

    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"], consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"])
    fun updatePerson(@RequestBody person: PersonVO) : PersonVO {
        return personService.updatePerson(person)
    }

    @DeleteMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun deletePerson(@PathVariable(value = "id") id : Long) : ResponseEntity<*> {
        personService.deletePerson(id)
        return ResponseEntity.noContent().build<Any>()
    }

}