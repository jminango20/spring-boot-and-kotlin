package com.jminango.controller

import com.jminango.data.vo.v1.PersonVO
import com.jminango.data.vo.v2.PersonVO as PersonVOV2
import com.jminango.services.PersonService
import com.jminango.utils.MediaType
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
class PersonController {


    @Autowired
    private lateinit var personService : PersonService

    @GetMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(
        summary = "Finds all people",
        description = "Finds all people",
        tags = ["People"],
        responses = [
           ApiResponse(
               description = "Success",
               responseCode = "200",
               content = [Content(array = ArraySchema(schema = Schema(implementation = PersonVO::class)))]
           ),
           ApiResponse(
               description = "No Content",
               responseCode = "204",
               content = [Content(schema = Schema(implementation = Unit::class))]
           ),
           ApiResponse(
               description = "Bad Request",
               responseCode = "400",
               content = [Content(schema = Schema(implementation = Unit::class))]
            ),
           ApiResponse(
               description = "Unauthorized",
               responseCode = "401",
               content = [Content(schema = Schema(implementation = Unit::class))]
            ),
           ApiResponse(
               description = "Not Found",
               responseCode = "404",
               content = [Content(schema = Schema(implementation = Unit::class))]
            ),
           ApiResponse(
               description = "Internal Error",
               responseCode = "500",
               content = [Content(schema = Schema(implementation = Unit::class))]
            )
        ]
    )
    fun findAll() : List<PersonVO>{
        return personService.findAll()
    }
    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(
        summary = "Finds a Person",
        description = "Finds a Person",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [Content(schema = Schema(implementation = PersonVO::class))]
            ),
            ApiResponse(
                description = "No Content",
                responseCode = "204",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Bad Request",
                responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized",
                responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Not Found",
                responseCode = "404",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal Error",
                responseCode = "500",
                content = [Content(schema = Schema(implementation = Unit::class))]
            )
        ]
    )
    fun findById(@PathVariable(value = "id") id : Long) : PersonVO {
        return personService.findById(id)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML], consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(
        summary = "Adds a new Person",
        description = "Adds a new Person",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [Content(schema = Schema(implementation = PersonVO::class))]
            ),
            ApiResponse(
                description = "Bad Request",
                responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized",
                responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal Error",
                responseCode = "500",
                content = [Content(schema = Schema(implementation = Unit::class))]
            )
        ]
    )
    fun createPerson(@RequestBody person: PersonVO) : PersonVO {
        return personService.createPerson(person)

    }

    @PostMapping(value = ["/v2"], produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML], consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun createPersonV2(@RequestBody person: PersonVOV2) : PersonVOV2 {
        return personService.createPersonV2(person)
    }

    @PutMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML], consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(
        summary = "Update a person's information",
        description = "Update a person's information",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [Content(schema = Schema(implementation = PersonVO::class))]
            ),
            ApiResponse(
                description = "No Content",
                responseCode = "204",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Bad Request",
                responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized",
                responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Not Found",
                responseCode = "404",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal Error",
                responseCode = "500",
                content = [Content(schema = Schema(implementation = Unit::class))]
            )
        ]
    )
    fun updatePerson(@RequestBody person: PersonVO) : PersonVO {
        return personService.updatePerson(person)
    }

    @DeleteMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML])
    @Operation(
        summary = "Delete a person",
        description = "Delete a person",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "No Content",
                responseCode = "204",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Bad Request",
                responseCode = "400",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Unauthorized",
                responseCode = "401",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Not Found",
                responseCode = "404",
                content = [Content(schema = Schema(implementation = Unit::class))]
            ),
            ApiResponse(
                description = "Internal Error",
                responseCode = "500",
                content = [Content(schema = Schema(implementation = Unit::class))]
            )
        ]
    )
    fun deletePerson(@PathVariable(value = "id") id : Long) : ResponseEntity<*> {
        personService.deletePerson(id)
        return ResponseEntity.noContent().build<Any>()
    }

}