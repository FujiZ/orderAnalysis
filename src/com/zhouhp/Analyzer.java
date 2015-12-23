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

    public void output(String filename){
        File file=new File(filename);
        BufferedWriter writer = null;
        try {
            writer=new BufferedWriter(new PrintWriter(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(Map.Entry<Long,Long> entry:actualTimeCountMap.entrySet())
            try {
                if (writer != null) {
                    writer.write("Time: " + entry.getKey() + ";Count: " + entry.getValue()+"\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        for(Map.Entry<Long,Long> entry:actualDistanceCountMap.entrySet()){
            try {
                if (writer != null) {
                    writer.write("Distance: "+entry.getKey()+";Count: "+entry.getValue()+"\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void outputTimeChart(){
        outputChart("time",actualTimeCountMap);
    }

    public void outputDistanceChart(){
        outputChart("distance",actualDistanceCountMap);
    }


    private void outputChart(String name,Map<Long,Long> dataMap){
        //先将数据取对数，放到另外一个map中
        Map<Long,Double> lgDataMap=new HashMap<Long, Double>();
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
