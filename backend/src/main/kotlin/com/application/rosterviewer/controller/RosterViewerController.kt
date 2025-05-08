package com.application.rosterviewer.controller

import com.application.rosterviewer.model.dto.RosterRequest
import com.application.rosterviewer.model.dto.RosterResponse
import com.application.rosterviewer.service.RosterViewerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/rosters")
class RosterViewerController(
    private val rosterViewerService: RosterViewerService
) {

    @PostMapping
    fun retrieveRosters(@RequestBody request: RosterRequest): ResponseEntity<RosterResponse> {
        val response = rosterViewerService.processRosterFile(request)
        return ResponseEntity.ok(RosterResponse())
    }

}