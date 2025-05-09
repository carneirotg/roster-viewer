package com.application.rosterviewer.controller

import com.application.rosterviewer.service.RosterViewerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File

@RestController
@RequestMapping("/api/v1/rosters")
class RosterViewerController(
    private val rosterViewerService: RosterViewerService
) {

    @PostMapping(consumes = ["multipart/form-data"], produces = ["application/json"])
    fun retrieveRosters(@RequestParam("file") file: MultipartFile): ResponseEntity<Map<String, String>> {
        val response = rosterViewerService.extractTextFromPdf(file)
        return ResponseEntity.ok(response)
    }

}