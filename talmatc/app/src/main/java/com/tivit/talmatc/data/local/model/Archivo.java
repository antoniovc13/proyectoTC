package com.tivit.talmatc.data.local.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Archivo extends BaseModel implements Parcelable {

    private String path;
    private String name;
    private String size;
    private Bitmap thumbnail;
    private int thumbnail_resource;
    private boolean imagen = false;
    private boolean add = false;
    private Long parentId;

    public Archivo() {
    }

    public Archivo(boolean add) {
        this.add = add;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getThumbnail_resource() {
        return thumbnail_resource;
    }

    public void setThumbnail_resource(int thumbnail_resource) {
        this.thumbnail_resource = thumbnail_resource;
    }

    public boolean isImagen() {
        return imagen;
    }

    public void setImagen(boolean imagen) {
        this.imagen = imagen;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeString(this.name);
        dest.writeString(this.size);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeInt(this.thumbnail_resource);
        dest.writeByte(this.imagen ? (byte) 1 : (byte) 0);
        dest.writeByte(this.add ? (byte) 1 : (byte) 0);
        dest.writeValue(this.parentId);
    }

    protected Archivo(Parcel in) {
        this.path = in.readString();
        this.name = in.readString();
        this.size = in.readString();
        this.thumbnail = in.readParcelable(Bitmap.class.getClassLoader());
        this.thumbnail_resource = in.readInt();
        this.imagen = in.readByte() != 0;
        this.add = in.readByte() != 0;
        this.parentId = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Archivo> CREATOR = new Creator<Archivo>() {
        @Override
        public Archivo createFromParcel(Parcel source) {
            return new Archivo(source);
        }

        @Override
        public Archivo[] newArray(int size) {
            return new Archivo[size];
        }
    };
}
