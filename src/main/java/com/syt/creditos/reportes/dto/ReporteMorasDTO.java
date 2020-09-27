package com.syt.creditos.reportes.dto;

// import java.math.BigInteger;


import java.util.ArrayList;

public class ReporteMorasDTO {

    Double total1;
    ArrayList<ReporteMorasUnoDTO> reporteMorasUnoDTO;
    ArrayList<ReporteMorasDosGraciaDTO> reporteMorasDosGraciaDTO;

    public ReporteMorasDTO(Double total1, ArrayList<ReporteMorasUnoDTO> reporteMorasUnoDTO, ArrayList<ReporteMorasDosGraciaDTO> reporteMorasDosGraciaDTO) {
        this.total1 = total1;
        this.reporteMorasUnoDTO = reporteMorasUnoDTO;
        this.reporteMorasDosGraciaDTO = reporteMorasDosGraciaDTO;
    }

    public ReporteMorasDTO() {
    }

    public Double getTotal1() {
        return total1;
    }

    public void setTotal1(Double total1) {
        this.total1 = total1;
    }

    public ArrayList<ReporteMorasUnoDTO> getReporteMorasUnoDTO() {
        return reporteMorasUnoDTO;
    }

    public void setReporteMorasUnoDTO(ArrayList<ReporteMorasUnoDTO> reporteMorasUnoDTO) {
        this.reporteMorasUnoDTO = reporteMorasUnoDTO;
    }

    public ArrayList<ReporteMorasDosGraciaDTO> getReporteMorasDosGraciaDTO() {
        return reporteMorasDosGraciaDTO;
    }

    public void setReporteMorasDosGraciaDTO(ArrayList<ReporteMorasDosGraciaDTO> reporteMorasDosGraciaDTO) {
        this.reporteMorasDosGraciaDTO = reporteMorasDosGraciaDTO;
    }
}
