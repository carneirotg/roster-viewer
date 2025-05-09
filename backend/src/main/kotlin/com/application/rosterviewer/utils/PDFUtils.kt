package com.application.rosterviewer.utils

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripperByArea
import java.awt.Rectangle
import java.io.File

object PDFUtils {
    fun extractShifts(file: File): Map<String, String> {
        val result = mutableMapOf<String, String>()

        PDDocument.load(file).use { document ->
            val page = document.getPage(0)
            val stripper = PDFTextStripperByArea()
            stripper.sortByPosition = true

            val rowHeight = 9
            val numRows = 30
            val startY = 129
            val nameX = 11
            val nameWidth = 40

            val shiftX = 150
            val shiftWidth = 300

            for (i in 0 until numRows) {
                val y = (startY + i * rowHeight)

                val nameRegion = Rectangle(nameX, y, nameWidth, 8)
                val shiftRegion = Rectangle(shiftX, y, shiftWidth, 8)

                stripper.addRegion("name_$i", nameRegion)
                stripper.addRegion("shift_$i", shiftRegion)
            }

            stripper.extractRegions(page)

            for (i in 0 until numRows) {
                val name = stripper.getTextForRegion("name_$i").trim()
                val shift = stripper.getTextForRegion("shift_$i").trim()

                name.split("\n").forEach { actualName ->
                    val cleanName = actualName.trim()
                    if (cleanName.isNotBlank()) {
                        result[cleanName] = shift
                    }
                }
            }
        }

        return result
    }
}