package com.application.rosterviewer.transformer

import com.application.rosterviewer.model.ShiftEntry
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripperByArea
import org.springframework.stereotype.Component
import java.awt.Rectangle
import java.io.File
import java.time.LocalDate
import java.time.Month
import java.time.Year
import java.time.YearMonth

@Component
class ShiftTransformer {

    fun processShift(file: File): List<ShiftEntry> {
        PDDocument.load(file).use { document ->
            val people = extractPeople(document)

            val month = extractMonth(document)
            val shifts = extractShifts(document, month)

            mergeShiftsAndPeople(people, shifts)
        }
    }

    private fun mergeShiftsAndPeople(people: List<String>, shifts: Unit) {
        TODO("Not yet implemented")
    }

    private fun extractShifts(document: PDDocument, monthName: String) {
        val year = Year.now().value
        val month = Month.valueOf(monthName.uppercase()) // e.g. "May"
        val daysInMonth = YearMonth.of(year, month).lengthOfMonth()

        val dates = (1..daysInMonth).map { LocalDate.of(year, month, it) }


    }

    fun extractPeople(document: PDDocument): List<String> {
        val page = document.getPage(0)
        val stripper = PDFTextStripperByArea().apply { sortByPosition = true }

        val nameX = 11
        val nameWidth = 40
        val rowHeight = 9
        val startY = 60
        val numRows = 40

        for (i in 0 until numRows) {
            val y = startY + i * rowHeight
            stripper.addRegion("name_$i", Rectangle(nameX, y, nameWidth, 8))
        }

        stripper.extractRegions(page)

        return (0 until numRows).flatMap { i ->
            stripper.getTextForRegion("name_$i")
                .lines() // splits on \n
                .map { it.trim() }
                .filter { it.isNotBlank() }
        }
    }

    fun extractMonth(document: PDDocument): String {
        val page = document.getPage(0)
        val stripper = PDFTextStripperByArea()
        val monthRegion = Rectangle(52, 42, 26, 10) // small box just around the month
        stripper.addRegion("month", monthRegion)
        stripper.extractRegions(page)
        return stripper.getTextForRegion("month").lines()[1]
    }

}