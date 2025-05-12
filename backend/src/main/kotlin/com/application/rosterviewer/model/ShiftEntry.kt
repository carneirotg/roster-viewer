package com.application.rosterviewer.model

import java.time.LocalDate

data class ShiftEntry(
    val name: String,
    val date: LocalDate,
    val shift: String,
    val location: String
)

