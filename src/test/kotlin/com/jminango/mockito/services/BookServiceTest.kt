package com.jminango.mockito.services

import com.jminango.exceptions.RequiredObjectsIsNullException
import com.jminango.mocks.MockBook
import com.jminango.repositories.BookRepository
import com.jminango.services.BookService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class BookServiceTest {

    private lateinit var inputObject : MockBook

    @InjectMocks
    private lateinit var bookService : BookService //Quando vou usar service, o mockito pega os mocks e injeta nele

    @Mock //Como BookRepository é usado dentro de BookService, quando chamo o @InjectMocks, esta anotação @Mock é injetada
    private lateinit var repository : BookRepository //Mockar um repository que permite "simular" interação com o DB

    @BeforeEach
    fun setUpMock(){
        inputObject = MockBook()
        MockitoAnnotations.openMocks(this) //Permite carregar os mocks - Simular a chamada a DB quando chamo ao service
    }

    @Test
    fun findAll() {
        val listBook = inputObject.mockEntityList()
        `when`(repository.findAll()).thenReturn(listBook)

        val listResult = bookService.findAll()

        assertNotNull(listResult)
        assertEquals(14, listResult.size)

        for (result in listResult){
            assertNotNull(result.links)
            assertNotNull(result.key)
            assertEquals("Some Title${result.key}", result.title)
            assertEquals("Some Author${result.key}", result.author)
            assertEquals(25.0, result.price)
            assertTrue(result.links.toString().contains( "</book/v1/${result.key}>;rel=\"self\""))
        }
    }

    @Test
    fun findById() {
        val book = inputObject.mockEntity(1)
        book.id = 1L
        `when`(repository.findById(1)).thenReturn(Optional.of(book)) //retorno o que foi mockado acima como book

        val result = bookService.findById(1)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains( "</book/v1/1>;rel=\"self\""))
        assertEquals("Some Title1", result.title)
        assertEquals("Some Author1", result.author)
        assertEquals(25.0, result.price)
    }

    @Test
    fun createBook() {
        val entity = inputObject.mockEntity(1)
        val persistent = entity.copy() //gravada no DB
        persistent.id = 1

        `when`(repository.save(entity)).thenReturn(persistent) //salvo minha entity e retorno o que foi salvo

        val entityVO = inputObject.mockVO(1)
        val result = bookService.createBook(entityVO)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains( "</book/v1/1>;rel=\"self\""))
        assertEquals("Some Title1", result.title)
        assertEquals("Some Author1", result.author)
        assertEquals(25.0, result.price)
    }


    @Test //mock me permite mockar o que esta dentro do service.update -> find and repository
    fun updateBook() {
        val entity = inputObject.mockEntity(1)

        val persistent = entity.copy()
        persistent.id = 1

        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        `when`(repository.save(entity)).thenReturn(persistent)

        val entityVO = inputObject.mockVO(1)
        val result = bookService.updateBook(entityVO)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains( "</book/v1/1>;rel=\"self\""))
        assertEquals("Some Title1", result.title)
        assertEquals("Some Author1", result.author)
    }

    @Test
    fun failUpdateBookNull() {
        val exception : Exception = assertThrows(RequiredObjectsIsNullException::class.java){
            bookService.updateBook(null)
        }
        val expectedMessage = "It is not allowed to persist a null object"
        val actualMessage = exception.message

        assertEquals(expectedMessage, actualMessage)
        assertTrue(actualMessage!!.contains(expectedMessage))
    }

    @Test
    fun deleteBook() {
        val entity = inputObject.mockEntity(1)
        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        bookService.deleteBook(1)
    }
}