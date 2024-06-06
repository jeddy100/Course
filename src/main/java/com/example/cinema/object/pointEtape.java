package com.example.cinema.object;

public class pointEtape {
    Long id;
    String nometape;
    double nbpoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNometape() {
        return nometape;
    }

    public void setNometape(String nometape) {
        this.nometape = nometape;
    }

    public double getNbpoints() {
        return nbpoints;
    }

    public void setNbpoints(double nbpoints) {
        this.nbpoints = nbpoints;
    }

    public pointEtape(Long id, String nometape, double nbpoints) {
        this.id = id;
        this.nometape = nometape;
        this.nbpoints = nbpoints;
    }

    public pointEtape(String nometape, double nbpoints) {
        this.nometape = nometape;
        this.nbpoints = nbpoints;
    }
}
