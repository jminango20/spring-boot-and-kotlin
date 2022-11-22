package com.jminango.mapper.custom

import com.jminango.data.vo.v2.PersonVO
import com.jminango.models.Person
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonMapper {

    fun mapEntityToVO(person : Person) : PersonVO {
        val personVO = PersonVO()
        personVO.id = person.id
        personVO.firstName = person.firstName
        personVO.lastName = person.lastName
        personVO.address = person.address
        personVO.gender = person.gender
        personVO.birthDay = Date() //Deveria ter em Entity mas só vamos a usar um mock
        return personVO
    }

    fun mapVOToEntity(personVO : PersonVO) : Person {
        val person = Person()
        person.id = personVO.id
        person.firstName = personVO.firstName
        person.lastName = personVO.lastName
        person.address = personVO.address
        person.gender = personVO.gender
        return person
    }

}