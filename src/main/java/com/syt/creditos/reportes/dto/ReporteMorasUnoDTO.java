package com.syt.creditos.reportes.dto;

// import java.math.BigInteger;


public class ReporteMorasUnoDTO {


    Long idPrestamo;
    String primerNombre;
    String primerApellido;
    Double montoAsignado;
    Double moraAsignada;
    int cuotasAtrasadas;
    Long diasAtraso;
    Double totalMora;


    public ReporteMorasUnoDTO(Long idPrestamo, String primerNombre, String primerApellido, Double montoAsignado, Double moraAsignada, int cuotasAtrasadas, Long diasAtraso, Double totalMora) {
        this.idPrestamo = idPrestamo;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.montoAsignado = montoAsignado;
        this.moraAsignada = moraAsignada;
        this.cuotasAtrasadas = cuotasAtrasadas;
        this.diasAtraso = diasAtraso;
        this.totalMora = totalMora;
    }

    public ReporteMorasUnoDTO() {
    }

    public int getCuotasAtrasadas() {
        return cuotasAtrasadas;
    }

    public void setCuotasAtrasadas(int cuotasAtrasadas) {
        this.cuotasAtrasadas = cuotasAtrasadas;
    }

    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public Double getMontoAsignado() {
        return montoAsignado;
    }

    public void setMontoAsignado(Double montoAsignado) {
        this.montoAsignado = montoAsignado;
    }

    public Double getMoraAsignada() {
        return moraAsignada;
    }

    public void setMoraAsignada(Double moraAsignada) {
        this.moraAsignada = moraAsignada;
    }

    public Long getDiasAtraso() {
        return diasAtraso;
    }

    public void setDiasAtraso(Long diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    public Double getTotalMora() {
        return totalMora;
    }

    public void setTotalMora(Double totalMora) {
        this.totalMora = totalMora;
    }
}
