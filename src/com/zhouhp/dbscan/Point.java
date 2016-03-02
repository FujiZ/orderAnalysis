package com.zhouhp.dbscan;


/**
 * Created by fuji on 16-1-19.
 */
public class Point {
    private double x;
    private double y;
    private boolean visited=false;
    private int cid=0;


    public boolean isVisited(){
        return visited;
    }

    public void setVisited(boolean visited){
        this.visited=visited;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point(double x,double y){
        this.x=x;
        this.y=y;
    }

    public Point(String str){
        String[] p=str.split(",");
        this.x=Integer.parseInt(p[0]);
        this.y=Integer.parseInt(p[1]);
    }

    @Override
    public String toString(){
        return this.x+","+this.y;
    }

    @Override
    public boolean equals(Object obj){
        if(this==obj)
            return true;
        if(obj==null||!(obj instanceof Point))
            return false;
        Point tarPoint=(Point)obj;
        return this.x==tarPoint.x&&this.y==tarPoint.y;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}




