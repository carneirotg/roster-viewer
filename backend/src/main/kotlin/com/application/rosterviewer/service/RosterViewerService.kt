package com.application.rosterviewer.service

import com.application.rosterviewer.transformer.ShiftTransformer
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class RosterViewerService(
    private val shiftTransformer: ShiftTransformer
) {

    fun extractTextFromPdf(request: MultipartFile): Map<String, List<String>> {
        val file = multipartToTempFile(request)
        val people = shiftTransformer.extractPeople(file)
        return mapOf("people" to people)
    }

    private fun multipartToTempFile(multipart: MultipartFile): File {
        val tempFile = File.createTempFile("upload-", ".pdf")
        multipart.transferTo(tempFile)
        return tempFile
    }

}