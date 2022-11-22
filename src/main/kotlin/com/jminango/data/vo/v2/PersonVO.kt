package com.jminango.data.vo.v2

import java.util.*

data class PersonVO(
    var id : Long = 0L,
    var firstName : String = "",
    var lastName : String = "",
    var address : String = "",
    var gender : String = "",
    var birthDay : Date? = null
)
