package com.project.barbeshop.controllers;


import com.project.barbeshop.ExportAdapter;
import com.project.barbeshop.UserExport;
import com.project.barbeshop.adapter.ExcelAdapter;
import com.project.barbeshop.adapter.PdfAdapter;
import com.project.barbeshop.dto.UserMapper;
import com.project.barbeshop.dto.UserResponseDto;
import com.project.barbeshop.entities.UserEntity;
import com.project.barbeshop.services.UserExcelExportService;
import com.project.barbeshop.services.UserPdfService;
import com.project.barbeshop.services.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {


    private final UserService userService;
    private final UserMapper userMapper;
    private final UserExcelExportService excelExportService;
    private final UserPdfService pdfService;


    public UserController(UserService userService, UserMapper userMapper,
                          UserExcelExportService userExcelExportService,
                          UserPdfService userPdfService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.excelExportService = userExcelExportService;
        this.pdfService = userPdfService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity currentUser = (UserEntity) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> allUsers() {
        List<UserEntity> users = userService.allUsers();
        List<UserResponseDto> userResponseDtos = userMapper.toDtoList(users);
        return ResponseEntity.ok(userResponseDtos);
    }

    @GetMapping("/export/excel")
    public ResponseEntity<InputStreamResource> exportUsersToExcel() throws IOException, IOException {
        List<UserEntity> users = userService.allUsers();

        UserExport excelUsersExport = new ExcelAdapter();
        ExportAdapter exportAdapter = new ExportAdapter(excelUsersExport);
        ByteArrayInputStream in = exportAdapter.execute(users);


        //ByteArrayInputStream in = excelExportService.exportUserDataToExcel(users);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=usuarios.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }

    /**
     * Endpoint para descargar el PDF en lugar de visualizarlo en el navegador
     */
    @GetMapping("/export/pdf")
    public ResponseEntity<InputStreamResource> downloadUsersPdfReport() {
        List<UserEntity> users = userService.allUsers();

        UserExport pdfUsersExport = new PdfAdapter();
        ExportAdapter exportAdapter = new ExportAdapter(pdfUsersExport);
        ByteArrayInputStream pdfStream = exportAdapter.execute(users);
        //ByteArrayInputStream pdfStream = pdfService.generateUsersPdfReport();

        // Generar nombre de archivo con fecha y hora actual
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = "usuarios_" + timestamp + ".pdf";

        // Configurar headers para forzar la descarga
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdfStream));
    }

}
