package com.jminango.models

import jakarta.persistence.*

@Entity
@Table(name = "person_table")
data class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0L,

    @Column(name = "first_name", nullable = false, length = 80)
    var firstName : String = "",

    @Column(name = "last_name", nullable = false, length = 80)
    var lastName : String = "",

    @Column(nullable = false, length = 100)
    var address : String = "",

    @Column(nullable = false, length = 20)
    var gender : String = ""
)
