package com.util;

import com.example.Laporan.model.Absensi;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;

import java.io.OutputStream;
import java.util.List;

public class PdfGenerator {

    public static void generateLaporan(
            OutputStream out,
            List<Absensi> list,
            String judul) {

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Judul
        Paragraph title = new Paragraph(judul);
        title.setBold();
        title.setFontSize(14);
        document.add(title);
        document.add(new Paragraph("\n"));

        // Tabel
        Table table = new Table(5);

        table.addHeaderCell("nama");
        table.addHeaderCell("Tanggal");
        table.addHeaderCell("Status");
        table.addHeaderCell("Blok");
        table.addHeaderCell("Panen");

        for (Absensi a : list) {
            table.addCell(a.getNama());
            table.addCell(a.getTanggal().toString());
            table.addCell(a.getStatus());
            table.addCell(a.getBlok());
            table.addCell(String.valueOf(a.getJumlahPanen()));
        }

        document.add(table);
        document.close();
    }
}
