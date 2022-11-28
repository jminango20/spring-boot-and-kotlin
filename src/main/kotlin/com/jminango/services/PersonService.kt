package com.jminango.services

import com.jminango.controller.PersonController
import com.jminango.data.vo.v1.PersonVO
import com.jminango.exceptions.RequiredObjectsIsNullException
import com.jminango.data.vo.v2.PersonVO as PersonVOV2
import com.jminango.exceptions.ResourceNotFoundException
import com.jminango.mapper.DozerMapper
import com.jminango.mapper.custom.PersonMapper
import com.jminango.models.Person
import com.jminango.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var personRepository : PersonRepository

    @Autowired
    private lateinit var personMapper : PersonMapper

    private val logger = Logger.getLogger(PersonService::class.java.name)

    private val mapper = DozerMapper

    fun findAll() : List<PersonVO> {
        logger.info("Finding all people!")
        val listPerson = personRepository.findAll()
        val listPersonVO = mapper.parseListObjects(listPerson, PersonVO::class.java)
        for (personVO in listPersonVO){
            val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
            personVO.add(withSelfRel)
        }
        return listPersonVO
    }

    fun findById(id: Long) : PersonVO {
        logger.info("Finding person! with id $id")
        val person = personRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this Id $id") }
        val personVO = mapper.parseObject(person, PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun createPerson(personVO : PersonVO?) : PersonVO {
        logger.info("Create a person with name!")
        if (personVO == null) throw  RequiredObjectsIsNullException()
        val entity : Person = personRepository.save(mapper.parseObject(personVO, Person::class.java))
        val personVO = mapper.parseObject(entity, PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun createPersonV2(personVOV2 : PersonVOV2) : PersonVOV2 {
        logger.info("Create a person with name ${personVOV2.firstName}!")
        val entity : Person = personRepository.save(personMapper.mapVOToEntity(personVOV2))
        return personMapper.mapEntityToVO(entity)
    }
    fun updatePerson(personVO: PersonVO?) : PersonVO {
        logger.info("Updating a person with Id")

        if (personVO == null) throw  RequiredObjectsIsNullException()

        val person = mapper.parseObject(personVO, Person::class.java)

        val entity = personRepository.findById(person.id)
            .orElseThrow{ResourceNotFoundException("No records found for this Id")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        val personVO = mapper.parseObject(personRepository.save(entity), PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        return personVO.add(withSelfRel)
    }
    fun deletePerson(id : Long) {
        logger.info("Delete a person with id ${id} !")

        val entity = personRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this Id ${id}") }

        personRepository.delete(entity)

    }



}