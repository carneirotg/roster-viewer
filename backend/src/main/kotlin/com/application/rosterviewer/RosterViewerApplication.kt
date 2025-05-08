package com.application.rosterviewer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RosterViewerApplication

fun main(args: Array<String>) {
	runApplication<RosterViewerApplication>(*args)
}
