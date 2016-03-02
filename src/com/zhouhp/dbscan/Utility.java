package com.zhouhp.dbscan;

import com.zhouhp.Calculator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by fuji on 16-1-19.
 */
public final class Utility {
    //计算两点之间的距离
    public static double getDistance(Point p,Point q){
        return Calculator.calDistance(p.getX(),p.getY(),q.getX(),q.getY());
    }

    public static Set<Point> getNeighbors(Set<Point> pointSet, Point p, int e){

        Set<Point> tmpSet=new HashSet<>();

        for (Point q : pointSet) {
            if (getDistance(p, q) <= e) {
                tmpSet.add(q);
            }
        }
        return tmpSet;

    }

    //显示聚类的结果
    public static void display(List<Set<Point>> resultList){
        System.out.println("输出结果");
        int index=1;
        for (Set<Point> cSet : resultList) {
            if (cSet.isEmpty()) {
                continue;
            }
            System.out.println("-----第" + index + "个聚类-----");
            cSet.forEach(System.out::println);
            index++;
        }
    }

    public static void output(Set<Point> resultSet,String filename){
        FileWriter fw=null;
        BufferedWriter writer=null;
        try {
            fw=new FileWriter(filename);
            writer=new BufferedWriter(fw);
            for(Point result:resultSet){
                writer.write(result.toString());
                writer.newLine();
            }
            writer.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}





