package com.tivit.talmatc.base.generic;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.utils.CommonUtils;

import java.util.List;

public class GenericSpinnerAdapter extends ArrayAdapter {

    private final String TAG = CommonUtils.getClassName(this.getClass());
    private Context context;
    private int resourceId;
    private List<GenericSpinnerItem> data;
    private String hint;
    private boolean supportNull = false;

    public GenericSpinnerAdapter(Context context, int resourceId, List<GenericSpinnerItem> data) {
        super(context, resourceId, data);
        this.context = context;
        this.resourceId = resourceId;
        this.data = data;
    }

    @Override
    public int getCount() {
        return hint != null ? data.size() + 1 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return position == 0 && hint != null ? null : data.get(hint != null ? position - 1 : position).getObj();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (position == 0 && hint != null && !supportNull) {
            return getNullView(parent);
        } else {
            return getCustomView((hint != null ? position - 1 : position), convertView, parent, resourceId);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == 0 && hint != null) {
            return getHintView(parent);
        } else {
            return getCustomView((hint != null ? position - 1 : position), convertView, parent, R.layout.spinner_item);
        }
    }

    public View getCustomView(int position, View view, ViewGroup parent, int lytResource) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(lytResource, parent, false);

        String text = null;
        if (position >= 0) {
            GenericSpinnerItem gsi = data.get(position);
            text = gsi.getText();
        } else {
            text = hint;
        }
        if (lytResource == android.R.layout.simple_list_item_single_choice) {
            ((CheckedTextView) view.findViewById(android.R.id.text1)).setText(text);
        } else if (lytResource == android.R.layout.simple_spinner_item) {
            ((TextView) view.findViewById(android.R.id.text1)).setText(text);
        } else if (lytResource == R.layout.spinner_item) {
            ((TextView) view.findViewById(R.id.text1)).setText(text);
        } else {
            Log.e(TAG, "FALTA IMPLEMENTACION EN GENERICSPINNERADAPTER");
        }
        return view;
    }

    public View getHintView(ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.spinner_item, parent, false);
        ((TextView) view.findViewById(R.id.text1)).setText(hint);
        ((TextView) view.findViewById(R.id.text1)).setPadding(0,0,8,0);
        ((TextView) view.findViewById(R.id.text1)).setTextColor(context.getResources().getColor(R.color.half_black));
        return view;
    }

    public View getNullView(ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.empty, parent, false);
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setSupportNull(boolean supportNull) {
        this.supportNull = supportNull;
    }

    //Este metodo obtiene la posicion del objeto dentro del arreglo
    //Para usarlo, el objeto del adapter debe implementar el metodo equals()!!
    public Integer getPositionByObject(Object obj) {
        if (obj == null) {
            return null;
        }
        for (int i = 0; i < getCount(); i++) {
            if (obj.equals(getItem(i))) {
                return i;
            }
        }
        return null;
    }

    public List<GenericSpinnerItem> getData() {
        return data;
    }

    public void setData(List<GenericSpinnerItem> data) {
        this.data = data;
    }

}
