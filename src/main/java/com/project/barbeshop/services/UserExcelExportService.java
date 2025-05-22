package com.project.barbeshop.services;

import com.project.barbeshop.entities.UserEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class UserExcelExportService {


    public ByteArrayInputStream exportUserDataToExcel(List<UserEntity> users) throws IOException {
        String[] columns = {"ID", "Nombre Completo", "Email"};
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // Crear la hoja Excel
            Sheet sheet = workbook.createSheet("Usuarios");

            // Crear la fila de encabezado
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            // Crear fila de encabezado
            Row headerRow = sheet.createRow(0);

            // Crear celdas de encabezado
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // Llenar datos
            int rowNum = 1;
            for (UserEntity user : users) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getFullName());
                row.createCell(2).setCellValue(user.getEmail());
            }

            // Ajustar ancho de columnas automáticamente
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }


            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
