package com.tivit.talmatc.data.local.model;

import java.io.Serializable;

/**
 * Created by Antonio.Valdivieso on 08/03/2018.
 */

public class Flight extends BaseModel implements Serializable{

    // ATTRIBUTES
    private String code;
    private String descripcion;
    private String pea;
    private String eta;
    private String etd;
    private String countElements;

    private String estadoCode;
    private String estadoDescripcion;
    private String ruta;

    private String observacion;

    //image
    private int drawable;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoCode() {
        return estadoCode;
    }

    public void setEstadoCode(String estadoCode) {
        this.estadoCode = estadoCode;
    }

    public String getEstadoDescripcion() {
        return estadoDescripcion;
    }

    public void setEstadoDescripcion(String estadoDescripcion) {
        this.estadoDescripcion = estadoDescripcion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getEtd() {
        return etd;
    }

    public void setEtd(String etd) {
        this.etd = etd;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }


    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    @Override
    public String toString() {
        /*return "Flight{" +
                "descripcion='" + descripcion + '\'' +
                '}';
        */
        return descripcion;
    }

    public String getPea() {
        return pea;
    }

    public void setPea(String pea) {
        this.pea = pea;
    }

    public String getCountElements() {
        return countElements;
    }

    public void setCountElements(String countElements) {
        this.countElements = countElements;
    }
}
