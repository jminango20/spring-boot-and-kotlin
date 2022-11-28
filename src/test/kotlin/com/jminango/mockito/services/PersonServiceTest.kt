package com.jminango.mockito.services

import com.jminango.mocks.MockPerson
import com.jminango.repositories.PersonRepository
import com.jminango.services.PersonService
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
internal class PersonServiceTest {

    private lateinit var inputObject : MockPerson

    @InjectMocks
    private lateinit var personService : PersonService //Quando vou usar service, o mockito pega os mocks e injeta nele

    @Mock //Como PersonRepository é usado dentro de PersonService, quando chamo o @InjectMocks, esta anotação @Mock é injetada
    private lateinit var repository : PersonRepository //Mockar um repository que permite "simular" interação com o DB

    @BeforeEach
    fun setUpMock(){
        inputObject = MockPerson()
        MockitoAnnotations.openMocks(this) //Permite carregar os mocks - Simular a chamada a DB quando chamo ao service
    }

    @Test
    fun findAll() {
        val listPerson = inputObject.mockEntityList()
        `when`(repository.findAll()).thenReturn(listPerson)

        val listResult = personService.findAll()

        assertNotNull(listResult)
        assertEquals(14, listResult.size)

        for (result in listResult){
            assertNotNull(result.links)
            assertNotNull(result.key)
            assertEquals("Address Test${result.key}", result.address)
            assertEquals("First Name Test${result.key}", result.firstName)
            assertEquals("Last Name Test${result.key}", result.lastName)
        }
    }

    @Test
    fun findById() {
        val person = inputObject.mockEntity(1)
        person.id = 1L
        `when`(repository.findById(1)).thenReturn(Optional.of(person)) //retorno o que foi mockado acima como person

        val result = personService.findById(1)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains( "</person/v1/1>;rel=\"self\""))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun createPerson() {
        val entity = inputObject.mockEntity(1)
        val persistent = entity.copy() //gravada no DB
        persistent.id = 1

        `when`(repository.save(entity)).thenReturn(persistent) //salvo minha entity e retorno o que foi salvo

        val entityVO = inputObject.mockVO(1)
        val result = personService.createPerson(entityVO)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains( "</person/v1/1>;rel=\"self\""))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test //mock me permite mockar o que esta dentro do service.update -> find and repository
    fun updatePerson() {
        val entity = inputObject.mockEntity(1)

        val persistent = entity.copy()
        persistent.id = 1

        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        `when`(repository.save(entity)).thenReturn(persistent)

        val entityVO = inputObject.mockVO(1)
        val result = personService.updatePerson(entityVO)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains( "</person/v1/1>;rel=\"self\""))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun deletePerson() {
        val entity = inputObject.mockEntity(1)
        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        personService.deletePerson(1)
    }
}