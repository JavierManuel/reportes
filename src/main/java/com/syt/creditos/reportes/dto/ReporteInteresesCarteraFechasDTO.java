package com.syt.creditos.reportes.dto;

// import java.math.BigInteger;


public class ReporteInteresesCarteraFechasDTO {


    Long idPrestamo;
    String primerNombre;
    String primerApellido;
    Double montoAsignado;
    Integer plazoMeses;
    String nombrePlan;
    String garantia;
    Double sumaTotal;
    Boolean estadoInteres;
    Integer estadoPerdon;
    String nombrePerdon;

    public ReporteInteresesCarteraFechasDTO(Long idPrestamo, String primerNombre, String primerApellido, Double montoAsignado, Integer plazoMeses, String nombrePlan, String garantia, Double sumaTotal, Boolean estadoInteres, Integer estadoPerdon, String nombrePerdon) {
        this.idPrestamo = idPrestamo;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.montoAsignado = montoAsignado;
        this.plazoMeses = plazoMeses;
        this.nombrePlan = nombrePlan;
        this.garantia = garantia;
        this.sumaTotal = sumaTotal;
        this.estadoInteres = estadoInteres;
        this.estadoPerdon = estadoPerdon;
        this.nombrePerdon = nombrePerdon;
    }

    public ReporteInteresesCarteraFechasDTO() {
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

    public Integer getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(Integer plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public Double getSumaTotal() {
        return sumaTotal;
    }

    public void setSumaTotal(Double sumaTotal) {
        this.sumaTotal = sumaTotal;
    }

    public Boolean getEstadoInteres() {
        return estadoInteres;
    }

    public void setEstadoInteres(Boolean estadoInteres) {
        this.estadoInteres = estadoInteres;
    }

    public Integer getEstadoPerdon() {
        return estadoPerdon;
    }

    public void setEstadoPerdon(Integer estadoPerdon) {
        this.estadoPerdon = estadoPerdon;
    }

    public String getNombrePerdon() {
        return nombrePerdon;
    }

    public void setNombrePerdon(String nombrePerdon) {
        this.nombrePerdon = nombrePerdon;
    }
}
