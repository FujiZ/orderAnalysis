package com.zhouhp;


public class Main {

    public static void main(String[] args) {
        Analyzer analyzer=new Analyzer("/home/fuji/tmp/dataAug.txt");
        analyzer.run();
        analyzer.outputTime("/home/fuji/tmp/time.txt");
        analyzer.outputDistance("/home/fuji/tmp/distance.txt");
        analyzer.outputTimeChart();
        analyzer.outputDistanceChart();

    }
}
