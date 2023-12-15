package org.kairosdb.core.http.rest;

import java.io.Serializable;

public class InterpolationData implements Serializable,Comparable {
    private long t;
    private double v;

    public InterpolationData(long timestamp,double value){
        this.t = timestamp;
        this.v = value;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    @Override
    public int compareTo(Object o) {
        return Long.compare(this.t,((InterpolationData)o).getT());
    }
}
