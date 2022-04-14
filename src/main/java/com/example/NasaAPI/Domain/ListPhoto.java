package com.example.NasaAPI.Domain;

import java.util.Arrays;

public class ListPhoto {
    public ListPhoto() {
    }

    private Photos[] photos;

    public Photos[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photos[] photos) {
        this.photos = photos;
    }

    @Override
    public String toString() {
        return "PhotoList [photos=" + Arrays.toString(photos) + "]";
    }
}
