package com.jminango.models

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "books")
data class Book(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0L,

    @Column(nullable = false, length = 180)
    var author : String = "",

    @Column(name = "launch_date", nullable = true)
    var launchDate : Date? = null,

    @Column(nullable = false)
    var price : Double = 0.0,

    @Column(nullable = false, length = 250)
    var title : String = ""
)