package com.jminango.data.vo.v1

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("id", "address", "first_name", "last_name", "gender")
data class PersonVO(
    var id : Long = 0L,

    @field:JsonProperty("first_name")
    var firstName : String = "",

    @field:JsonProperty("last_name")
    var lastName : String = "",

    var address : String = "",

    @JsonIgnore
    var gender : String = ""
)
