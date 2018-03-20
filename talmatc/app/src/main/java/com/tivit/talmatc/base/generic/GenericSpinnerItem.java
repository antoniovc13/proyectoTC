package com.tivit.talmatc.base.generic;

import com.tivit.talmatc.data.local.model.Flight;
import com.tivit.talmatc.data.local.model.Parameter;

import java.util.ArrayList;
import java.util.List;

public class GenericSpinnerItem {

    // ATTRIBUTES
    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    // METHODS
    public static List<GenericSpinnerItem> convertFromList(List lista) {
        List<GenericSpinnerItem> listaGsi = new ArrayList<>();
        for (Object o : lista) {
            GenericSpinnerItem gsi = new GenericSpinnerItem();
            gsi.setObj(o);
            listaGsi.add(gsi);
        }
        return listaGsi;
    }

    public static GenericSpinnerItem convertFromObject(Object obj) {
        GenericSpinnerItem gsi = new GenericSpinnerItem();
        gsi.setObj(obj);
        return gsi;
    }

    public String getText() {
        if (obj instanceof String) {
            return obj + "";
        } else if (obj instanceof Parameter) {
            return ((Parameter) obj).getDescription();
        } else {
            return "Falta Implementar GenericSpinnerItem";
        }
    }

    public static List<String> convertFromFlight(List<Flight> lista) {
        List<String> listaGsi = new ArrayList<>();
        for (Flight o : lista) {
            listaGsi.add(o.getDescripcion());
        }
        return listaGsi;
    }

}
