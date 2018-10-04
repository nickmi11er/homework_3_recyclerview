package com.tinkoff.androidcourse

enum class Gender {
    MALE,
    FEMALE
}

data class Worker(var id: Int = 0,
                  var name: String? = null,
                  var photo: Int? = null,
                  var age: String? = null,
                  var position: String? = null,
                  var gender: Gender = Gender.MALE)
