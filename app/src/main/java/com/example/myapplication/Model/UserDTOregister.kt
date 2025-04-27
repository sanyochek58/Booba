package com.example.myapplication.Model

import java.time.LocalDate

data class UserDTOregister(
    val email: String,
    val password: String,
    val username: String,
    val birthOfDate: String
)
