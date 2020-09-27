package com.syt.creditos.reportes.dto;

// import java.math.BigInteger;


public class ReporteMorasDosGraciaDTO {


    Long idPrestamo;
    String primerNombre;
    String primerApellido;
    Double montoAsignado;
    Double moraAsignada;
    Long diasGracia;

    public ReporteMorasDosGraciaDTO(Long idPrestamo, String primerNombre, String primerApellido, Double montoAsignado, Double moraAsignada, Long diasGracia) {
        this.idPrestamo = idPrestamo;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.montoAsignado = montoAsignado;
        this.moraAsignada = moraAsignada;
        this.diasGracia = diasGracia;
    }

    public ReporteMorasDosGraciaDTO() {
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

    public Long getDiasGracia() {
        return diasGracia;
    }

    public void setDiasGracia(Long diasGracia) {
        this.diasGracia = diasGracia;
    }
}
