package com.application.rosterviewer.model

import java.time.LocalDate
import java.time.LocalTime

data class Shift(
    val location: String,
    val startTime: LocalTime,
    val endTime: LocalTime
)

