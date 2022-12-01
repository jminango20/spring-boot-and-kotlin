package com.jminango.controller

import com.jminango.data.vo.v1.BookVO
import com.jminango.services.BookService
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
@RequestMapping("/book/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
class BookController {


    @Autowired
    private lateinit var bookService : BookService

    @GetMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(
        summary = "Finds all people",
        description = "Finds all people",
        tags = ["People"],
        responses = [
           ApiResponse(
               description = "Success",
               responseCode = "200",
               content = [Content(array = ArraySchema(schema = Schema(implementation = BookVO::class)))]
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
    fun findAll() : List<BookVO>{
        return bookService.findAll()
    }
    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(
        summary = "Finds a Book",
        description = "Finds a Book",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [Content(schema = Schema(implementation = BookVO::class))]
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
    fun findById(@PathVariable(value = "id") id : Long) : BookVO {
        return bookService.findById(id)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML], consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(
        summary = "Adds a new Book",
        description = "Adds a new Book",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [Content(schema = Schema(implementation = BookVO::class))]
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
    fun createBook(@RequestBody book: BookVO) : BookVO {
        return bookService.createBook(book)

    }

    @PutMapping(produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML], consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    @Operation(
        summary = "Update a book's information",
        description = "Update a book's information",
        tags = ["People"],
        responses = [
            ApiResponse(
                description = "Success",
                responseCode = "200",
                content = [Content(schema = Schema(implementation = BookVO::class))]
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
    fun updateBook(@RequestBody book: BookVO) : BookVO {
        return bookService.updateBook(book)
    }

    @DeleteMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML])
    @Operation(
        summary = "Delete a book",
        description = "Delete a book",
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
    fun deleteBook(@PathVariable(value = "id") id : Long) : ResponseEntity<*> {
        bookService.deleteBook(id)
        return ResponseEntity.noContent().build<Any>()
    }

}