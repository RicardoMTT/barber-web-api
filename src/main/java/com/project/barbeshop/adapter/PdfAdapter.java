package com.project.barbeshop.adapter;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.project.barbeshop.UserExport;
import com.project.barbeshop.entities.UserEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class PdfAdapter implements UserExport {

    private static final int DEFAULT_MARGIN = 20;

    public PdfAdapter(){

    }

    @Override
    public ByteArrayInputStream execute(List<UserEntity> users) {
        Document document = new Document(PageSize.A4, DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN + 20, DEFAULT_MARGIN);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Definir la fuente para el título y el contenido
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

            PdfWriter writer = PdfWriter.getInstance(document, out);

            document.open();

            // Agregar título
            Paragraph title = new Paragraph("Titulo", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Agregar fecha del reporte
            Paragraph dateP = new Paragraph("Fecha: " +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                    FontFactory.getFont(FontFactory.HELVETICA, 10));
            dateP.setAlignment(Element.ALIGN_RIGHT);
            dateP.setSpacingAfter(20);
            document.add(dateP);

            // Crear tabla
            PdfPTable table = new PdfPTable(3); // 3 columnas
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 3, 3}); // Proporción de ancho de columnas

            // Agregar encabezados de tabla
            addTableHeader(table, headerFont);


            // Agregar datos de usuarios a la tabla
            for (UserEntity user : users) {
                addUserToTable(table, user, contentFont);
            }

            document.add(table);

            // Agregar información de resumen
            Paragraph summary = new Paragraph("Total de usuarios: " + users.size(),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10));
            summary.setAlignment(Element.ALIGN_RIGHT);
            summary.setSpacingBefore(20);
            document.add(summary);

            document.close();
            writer.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }



    private void addUserToTable(PdfPTable table, UserEntity user, Font font) {
        table.addCell(new PdfPCell(new Phrase(user.getId().toString(), font)));
        table.addCell(new PdfPCell(new Phrase(user.getFullName(), font)));
        table.addCell(new PdfPCell(new Phrase(user.getEmail(), font)));
    }


    private void addTableHeader(PdfPTable table, Font font) {
        String[] headers = {"ID", "Nombre Completo", "Email"};

        for (String header : headers) {
            PdfPCell cell = new PdfPCell();
            cell.setPadding(5);
            cell.setPhrase(new Phrase(header, font));
            table.addCell(cell);
        }
    }


}
