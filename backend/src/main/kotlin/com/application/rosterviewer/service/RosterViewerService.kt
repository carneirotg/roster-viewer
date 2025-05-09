package com.application.rosterviewer.service

import com.application.rosterviewer.model.Shift
import com.application.rosterviewer.utils.PDFUtils
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class RosterViewerService {

    fun extractTextFromPdf(request: MultipartFile): Map<String, String> {
        val file = multipartToTempFile(request)
        return PDFUtils.extractShifts(file)
    }

    private fun multipartToTempFile(multipart: MultipartFile): File {
        val tempFile = File.createTempFile("upload-", ".pdf")
        multipart.transferTo(tempFile)
        return tempFile
    }

}