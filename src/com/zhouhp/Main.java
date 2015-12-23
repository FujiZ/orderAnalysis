package com.zhouhp;




public class Main {

    public static void main(String[] args) {
        Analyzer analyzer=new Analyzer("/home/fuji/tmp/dataAug.txt");
        analyzer.run();
        analyzer.outputTimeChart();
        analyzer.outputDistanceChart();

    }
}
