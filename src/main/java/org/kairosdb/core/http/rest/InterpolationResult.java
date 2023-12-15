package org.kairosdb.core.http.rest;

import io.netty.util.internal.ConcurrentSet;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class InterpolationResult implements Serializable {
    private String tagName;
    private ConcurrentSet<InterpolationData> data;

    public InterpolationResult(String tagName,int size) {
        this.setTagName(tagName);
        this.data = new ConcurrentSet<>();
    }

    public void Add(InterpolationData pointData){
        this.data.add(pointData);
    }

    public String getTagName() {
        return tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    public List<InterpolationData> getData() {
        final List<InterpolationData> dataList = data.stream().collect(Collectors.toList());
        dataList.sort(new Comparator<InterpolationData>() {
            @Override
            public int compare(InterpolationData o1, InterpolationData o2) {
                return Long.valueOf(o1.getT()).compareTo(Long.valueOf(o2.getT()));
            }
        });

        return dataList;
    }


}
