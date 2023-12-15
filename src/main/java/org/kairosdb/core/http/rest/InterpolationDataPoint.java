package org.kairosdb.core.http.rest;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

import org.kairosdb.core.DataPoint;
import org.kairosdb.core.datastore.DataPointGroup;

import java.util.*;
import java.util.logging.Logger;

public class InterpolationDataPoint {
    private Map<Long,Double> _dataPointGroup;
    private Long bitState = 0L;
    private BitSet bitSet;
    private Map<Long,Double> innerDataPointMap = new HashMap<>();
    private final RangeMap<Long, Double>  longDoubleRangeMap = TreeRangeMap.create();

    private int dataSize;
    public InterpolationDataPoint(Map<Long,Double> dataPointGroup){
        _dataPointGroup = dataPointGroup;
        if(dataPointGroup == null)
            throw new NullPointerException("查询数据为空");


        long prev = 0;
        double prevValue = 0;

        for(Map.Entry<Long,Double> item : _dataPointGroup.entrySet()){
            if(prev == 0)
                prev = item.getKey();

            longDoubleRangeMap.put(Range.closed(prev, item.getKey()),
                    item.getValue());

            prev = item.getKey();
            prevValue = item.getValue();
        }

        longDoubleRangeMap.put(Range.atLeast(prev),prevValue);
    }

    public String toString(){
        return longDoubleRangeMap.toString();
    }

    public Double getValueByTimestamp(Long timestamp){
        return longDoubleRangeMap.get(timestamp);
    }

}
