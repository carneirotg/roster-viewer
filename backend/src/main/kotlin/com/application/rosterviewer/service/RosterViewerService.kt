package com.application.rosterviewer.service

import com.application.rosterviewer.model.dto.RosterRequest
import com.application.rosterviewer.utils.FileUtils
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.springframework.stereotype.Service

@Service
class RosterViewerService {

    fun processRosterFile(request: RosterRequest): String {
        val file = request.path?.let { FileUtils.openFile(it) }
        PDDocument.load(file).use { document ->
            return PDFTextStripper().getText(document)
        }
    }
}