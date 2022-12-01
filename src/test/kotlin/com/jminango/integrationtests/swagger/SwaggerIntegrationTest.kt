package com.jminango.integrationtests.swagger

import com.jminango.integrationtests.ConfigsTest
import com.jminango.integrationtests.testcontainers.AbstractIntegrationTest
import io.restassured.RestAssured
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest : AbstractIntegrationTest() {

	@Test
	fun shouldDisplaySwaggerUiPage() {
		val content = RestAssured.given()
			.basePath("/swagger-ui/index.html")
			.port(ConfigsTest.SERVER_PORT)
				.`when`()
			.get()
			.then()
			.statusCode(200)
			.extract()
			.body()
			.asString()

		assertTrue(content.contains("Swagger UI"))
	}

}