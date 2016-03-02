package com.zhouhp.dbscan;

import java.io.File;
import java.util.*;

/**
 * Created by fuji on 16-1-19.
 */
public class Dbscan {
    private int e;//ε半径
    private int minp;//密度阈值
    private Set<Point> pointSet;//存储原始样本点
    private static List<Set<Point>> resultList=new ArrayList<>();//存储最后的聚类结果

    public Dbscan(Set<Point> pointSet,int e,int minp){
        this.pointSet =pointSet;
        this.e=e;
        this.minp=minp;
    }

    private void expandCluster(Point p,Set<Point> neighbors,int clusterID){
        p.setCid(clusterID);
        for (Point q : neighbors) {
            if (!q.isVisited()) {
                q.setVisited(true);
                Set<Point> qNeighbors = Utility.getNeighbors(pointSet, q, e);
                if (qNeighbors.size() >= minp) {
                    for (Point tmpPoint : qNeighbors) {
                        if (tmpPoint.getCid() <= 0)
                            tmpPoint.setCid(clusterID);
                    }
                }
            }
            if(q.getCid()<=0)
                q.setCid(clusterID);
        }
    }

    public int applyDbscan(){
        int clusterID=0;
        boolean AllVisited=false;
        while (!AllVisited){
            AllVisited=true;
            for (Point p : pointSet) {
                if (p.isVisited())
                    continue;
                AllVisited = false;
                p.setVisited(true);
                Set<Point> neighbors = Utility.getNeighbors(pointSet, p, e);
                if (neighbors.size() < minp) {
                    if (p.getCid() <= 0)
                        p.setCid(-1);
                } else if (p.getCid() <= 0) {
                    clusterID++;
                    expandCluster(p, neighbors, clusterID);
                } else {
                    expandCluster(p, neighbors, p.getCid());
                }
            }
        }
        return clusterID;
    }

    public void genResultList(){
        if(resultList.isEmpty()){
            Map<Integer,Set<Point>> tmpMap=new HashMap<>();
            for(Point point:pointSet){
                if(point.getCid()<=0)
                    continue;
                Set<Point> tmpSet=tmpMap.get(point.getCid());
                if(tmpSet==null){
                    tmpSet=new HashSet<>();
                }
                tmpSet.add(point);
                tmpMap.put(point.getCid(),tmpSet);
            }
            resultList.addAll(tmpMap.values());
        }
    }

    public void outputResult(String dir){
        genResultList();
        mkdir(dir);
        int index=1;
        for(Set<Point> resultSet:resultList){
            Utility.output(resultSet,dir+index+".txt");
            index++;
        }
    }

    private boolean mkdir(String dir){
        File file=new File(dir);
        if(!file.exists()&&!file.isDirectory()) {
            return file.mkdirs();
        }
        return true;
    }

    public void displayResult(){
        Utility.display(resultList);
    }

}
