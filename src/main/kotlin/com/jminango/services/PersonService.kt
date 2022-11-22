package com.jminango.services

import com.jminango.data.vo.v1.PersonVO
import com.jminango.data.vo.v2.PersonVO as PersonVOV2
import com.jminango.exceptions.ResourceNotFoundException
import com.jminango.mapper.DozerMapper
import com.jminango.models.Person
import com.jminango.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var personRepository : PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    private val mapper = DozerMapper

    fun findAll() : List<PersonVO> {
        logger.info("Finding all people!")
        val listPerson = personRepository.findAll()
        return mapper.parseListObjects(listPerson, PersonVO::class.java)
    }

    fun findById(id: Long) : PersonVO {
        logger.info("Finding person! with id $id")
        val person = personRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this Id $id") }
        return mapper.parseObject(person, PersonVO::class.java)
    }

    fun createPerson(personVO : PersonVO) : PersonVO {
        logger.info("Create a person with name ${personVO.firstName}!")
        val entity : Person = personRepository.save(mapper.parseObject(personVO, Person::class.java))
        return mapper.parseObject(entity, PersonVO::class.java)
    }

    fun createPersonV2(personVOV2 : PersonVOV2) : PersonVOV2 {
        logger.info("Create a person with name ${personVOV2.firstName}!")
        val entity : Person = personRepository.save(mapper.parseObject(personVOV2, Person::class.java))
        return mapper.parseObject(entity, PersonVOV2::class.java)
    }
    fun updatePerson(personVO: PersonVO) : PersonVO {
        logger.info("Updating a person with Id ${personVO.id}")

        val person = mapper.parseObject(personVO, Person::class.java)

        val entity = personRepository.findById(person.id)
            .orElseThrow{ResourceNotFoundException("No records found for this Id")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return mapper.parseObject(personRepository.save(entity), PersonVO::class.java)
    }
    fun deletePerson(id : Long) {
        logger.info("Delete a person with id ${id} !")

        val entity = personRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this Id ${id}") }

        personRepository.delete(entity)

    }



}