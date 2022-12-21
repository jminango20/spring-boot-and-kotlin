package com.jminango.integrationtests.vo


data class PersonVO(
    var id : Long = 0L,
    var firstName : String = "",
    var lastName : String = "",
    var address : String = "",
    var gender : String = ""
)