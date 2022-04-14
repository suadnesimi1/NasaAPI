package com.example.NasaAPI.Domain;


import java.net.URL;
import java.util.Date;

public class Photos{

    private long id;
    private int sol;
    private Camera camera;
    private URL imgSrc;
    private Date earthDate;
    private Rover rover;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSol() {
        return sol;
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public URL getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(URL imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Date getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(Date earthDate) {
        this.earthDate = earthDate;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }


}
