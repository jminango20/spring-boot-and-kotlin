package com.jminango.integrationtests.controller.cors.withjson

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.jminango.integrationtests.ConfigsTest
import com.jminango.integrationtests.testcontainers.AbstractIntegrationTest
import com.jminango.integrationtests.vo.PersonVO
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonControllerCorsWithJson : AbstractIntegrationTest() {

	private lateinit var specification: RequestSpecification
	private lateinit var objectMapper: ObjectMapper
	private lateinit var personVO: PersonVO

	@BeforeAll
	fun setupTests(){
		objectMapper = ObjectMapper()
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
		personVO = PersonVO()
	}

	@Test
	@Order(1)
	fun testCreate() {
		mockPerson()

		specification = RequestSpecBuilder()
				.addHeader(
						ConfigsTest.HEADER_PARAM_ORIGIN,
						ConfigsTest.ORIGIN_GOOGLE
				)
					.setBasePath("/person/v1")
				.setPort(ConfigsTest.SERVER_PORT)
				.addFilter(RequestLoggingFilter(LogDetail.ALL))
				.addFilter(ResponseLoggingFilter(LogDetail.ALL))
				.build()

		val content = RestAssured.given()
				.spec(specification)
				.contentType(ConfigsTest.CONTENT_TYPE_JSON)
				.body(personVO)
				.`when`()
			.post()
			.then()
			.statusCode(200)
			.extract()
			.body()
			.asString()

		val createPerson = objectMapper.readValue(
				content,
				PersonVO::class.java
		)

		assertNotNull(createPerson.id)
		assertNotNull(createPerson.firstName)
		assertNotNull(createPerson.lastName)
		assertNotNull(createPerson.address)
		assertNotNull(createPerson.gender)

		assertTrue(createPerson.id > 0)

		assertEquals("Juan", createPerson.firstName)
		assertEquals("Minango", createPerson.lastName)
		assertEquals("Brasilia, DF, Brasil", createPerson.address)
		assertEquals("Male", createPerson.gender)

	}

	private fun mockPerson() {
		personVO.firstName = "Juan"
		personVO.lastName = "Minango"
		personVO.address = "Brasilia, DF, Brasil"
		personVO.gender = "Male"
	}

}