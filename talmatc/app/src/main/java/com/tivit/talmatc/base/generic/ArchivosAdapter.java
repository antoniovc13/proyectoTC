package com.tivit.talmatc.base.generic;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.data.local.model.Archivo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArchivosAdapter extends BaseAdapter {

    public static final int TYPE_LIST = 1;
    public static final int TYPE_GRID = 2;

    private final Context context;
    private List<Archivo> l_adjuntos;
    private int type = TYPE_LIST;
    private Map<Integer, Bitmap> mapThumbnailAdjuntos = new HashMap<>();

    public ArchivosAdapter(Context context, List<Archivo> l_adjuntos) {
        this.context = context;
        this.l_adjuntos = l_adjuntos;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getCount() {
        return l_adjuntos.size();
    }

    @Override
    public Object getItem(int position) {
        return l_adjuntos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Archivo adj = l_adjuntos.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_archivo, parent, false);
        }
        if (type == TYPE_LIST) {
            TextView noNombre = (TextView) convertView.findViewById(R.id.noNombre);
            noNombre.setText(adj.getName());
        }
        return convertView;
    }

    static class GridViewHolder {
        ImageView imgArchivo;
    }

}
