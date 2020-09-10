package com.syt.creditos.reportes.dto;

import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;

// import java.math.BigInteger;


public class ReportePagosDTO implements Collection<Object> {


    Long idPrestamo;
    String primerNombre;
    String primerApellido;
    Double abonoCapital;
    Double interes;
    Double cuotaSeguro;
    Double total;
    Double pagoExtraCapital;
    String fechaSistema;

    public ReportePagosDTO(Long idPrestamo, String primerNombre, String primerApellido, Double abonoCapital, Double interes, Double cuotaSeguro, Double total, Double pagoExtraCapital, String fechaSistema) {
        this.idPrestamo = idPrestamo;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.abonoCapital = abonoCapital;
        this.interes = interes;
        this.cuotaSeguro = cuotaSeguro;
        this.total = total;
        this.pagoExtraCapital = pagoExtraCapital;
        this.fechaSistema = fechaSistema;
    }

    public ReportePagosDTO() {
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

    public Double getAbonoCapital() {
        return abonoCapital;
    }

    public void setAbonoCapital(Double abonoCapital) {
        this.abonoCapital = abonoCapital;
    }

    public Double getInteres() {
        return interes;
    }

    public void setInteres(Double interes) {
        this.interes = interes;
    }

    public Double getCuotaSeguro() {
        return cuotaSeguro;
    }

    public void setCuotaSeguro(Double cuotaSeguro) {
        this.cuotaSeguro = cuotaSeguro;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPagoExtraCapital() {
        return pagoExtraCapital;
    }

    public void setPagoExtraCapital(Double pagoExtraCapital) {
        this.pagoExtraCapital = pagoExtraCapital;
    }

    public String getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(String fechaSistema) throws ParseException {

//        String date_s = s;

        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
//        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        Date date = dt.parse(date_s);

        // *** same for the format String below
//        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(dt1.format(date));


        this.fechaSistema = fechaSistema;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Object> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }
}
