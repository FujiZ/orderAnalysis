package com.zhouhp;

import java.io.*;
import java.util.*;

/**
 * Created by fuji on 15-12-21.
 */
public class Analyzer {

    public Analyzer(String filename){
        parser=new Parser(filename);
        actualTimeCountMap=new TreeMap<Long, Long>();
        actualDistanceCountMap=new TreeMap<Long, Long>();
    }

    public void run(){
        Iterator<OrderEntry> iterator=parser.getEntryIterator();
        while (iterator.hasNext()){
            OrderEntry orderEntry=iterator.next();
            if(orderEntry.hasTime()){
                Long time=orderEntry.getActualTime();
                increaseCount(time,actualTimeCountMap);
            }
            if(orderEntry.hasCoordinate()){
                Long distance=(long)orderEntry.getActualDistance();
                increaseCount(distance,actualDistanceCountMap);
            }
        }
    }

    public void outputTime(String filename){
        outputData(filename,actualTimeCountMap);

    }

    public void outputDistance(String filename){
        outputData(filename,actualDistanceCountMap);

    }
    public void outputTimeChart(){
        outputChart("time",actualTimeCountMap);
    }

    public void outputDistanceChart(){
        outputChart("distance",actualDistanceCountMap);
    }

    private void outputData(String filename,Map<Long,Long> dataMap){

        File file=new File(filename);
        FileWriter writer;
        try {
            writer=new FileWriter(file);
            for(Map.Entry<Long,Long> entry:dataMap.entrySet())
                writer.write(entry.getKey() + "," + entry.getValue()+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void outputChart(String name,Map<Long,Long> dataMap){
        //先将数据取对数，放到另外一个map中
        Map<Long,Double> lgDataMap=new HashMap<>();
        for(Map.Entry<Long,Long> entry:dataMap.entrySet()){
            Long lgKey=(long)Math.log(entry.getKey());
            Double lgValue=Math.log(entry.getValue());
            Double lgCount=lgDataMap.get(lgKey);
            if(lgCount==null){
                lgDataMap.put(lgKey,lgValue);
            }
            else{
                lgDataMap.put(lgKey,lgCount+lgValue);
            }
        }

        new LineChart(name,lgDataMap).createChart();

    }

    private void increaseCount(Long key,Map<Long,Long> countMap){
        Long count=countMap.get(key);
        if(count==null){
            countMap.put(key,1L);
        }
        else {
            countMap.put(key,count+1);
        }
    }

    private Parser parser;

    private Map<Long,Long> actualTimeCountMap;
    private Map<Long,Long> actualDistanceCountMap;

}
