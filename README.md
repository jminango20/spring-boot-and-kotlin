# spring-boot-and-kotlin

## Using Migration with Flyway
+ Add the dependencies to pom.xml
```
<dependency>
  <groupId>org.flywaydb</groupId>
	<artifactId>flyway-core</artifactId>
</dependency>
<dependency>
  <groupId>org.flywaydb</groupId>
	<artifactId>flyway-mysql</artifactId>
</dependency>
```
+ It was created a folder in src/resources/db/migration
+ Inside migration folder, it was created two files:
  * V1__Create_Table_Person.sql : which creates DB table
  * V2__Populate_Table_Person.sql : which populates with data the table poreviously created

+ It is started the server and flyway produces the migration to DB
