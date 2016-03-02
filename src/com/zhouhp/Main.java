package com.zhouhp;



public class Main {

    public static void main(String[] args) {
        Analyzer analyzer=new Analyzer("/home/fuji/tmp/temp.txt");
        analyzer.runDbScan(500,10,"/home/fuji/tmp/cluster0/");
        //analyzer.run();
        //analyzer.outputTime("/home/fuji/tmp/timeAug.txt");
        //analyzer.outputDistance("/home/fuji/tmp/distanceAug.txt");
        //analyzer.outputTimeChart();
        //analyzer.outputDistanceChart();

    }
}
