package com.application.rosterviewer.utils

import com.application.rosterviewer.model.dto.RosterResponse
import java.io.File

class FileUtils {
    companion object {
        fun openFile(path: String): File {
            return File(path)
        }
    }
}