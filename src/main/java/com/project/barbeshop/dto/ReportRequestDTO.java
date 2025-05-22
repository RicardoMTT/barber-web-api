package com.project.barbeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



public class ReportRequestDTO {

    private String title = "Reporte de Usuarios";
    private String companyName;
    private String companyLogo; // Base64 encoded image (opcional)
    private List<Integer> userIds; // Si se desea filtrar usuarios específicos
    private boolean includeHeader = true;
    private boolean includePageNumbers = true;

    public ReportRequestDTO() {
    }
    public ReportRequestDTO(String title, String companyName, String companyLogo, List<Integer> userIds, boolean includeHeader, boolean includePageNumbers) {
        this.title = title;
        this.companyName = companyName;
        this.companyLogo = companyLogo;
        this.userIds = userIds;
        this.includeHeader = includeHeader;
        this.includePageNumbers = includePageNumbers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public boolean isIncludeHeader() {
        return includeHeader;
    }

    public void setIncludeHeader(boolean includeHeader) {
        this.includeHeader = includeHeader;
    }

    public boolean isIncludePageNumbers() {
        return includePageNumbers;
    }

    public void setIncludePageNumbers(boolean includePageNumbers) {
        this.includePageNumbers = includePageNumbers;
    }
}
