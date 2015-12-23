package com.zhouhp;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by fuji on 15-12-23.
 */
public class LineChart{

    public LineChart(String name,Map<Long,Double> dataMap){
        this.name=name;
        this.dataMap=dataMap;

    }

    private DefaultCategoryDataset createDataset(){
        DefaultCategoryDataset lineDataSet=new DefaultCategoryDataset();

        String series=name;

        for(Map.Entry<Long,Double> entry:dataMap.entrySet()){
            lineDataSet.addValue(entry.getValue(),series,entry.getKey());
        }

        return lineDataSet;

    }

    public void createChart(){
        JFreeChart chart= ChartFactory.createLineChart(null,    //报表题目
                name,   //横轴
                "count",    //纵轴
                this.createDataset(),   //数据集
                PlotOrientation.VERTICAL,   //图标方向垂直
                true,   //显示图例
                false,  //不用生成工具
                false   //不生成URL地址
                );

        //生成图形
        CategoryPlot plot=chart.getCategoryPlot();
        //图像属性部分
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinesVisible(true);   //设置背景网格线是否可见
        plot.setDomainGridlinePaint(Color.black);
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setNoDataMessage("没有数据");//没有数据时显示的文字说明。
        //数据轴属性部分
        NumberAxis rangeAxis=(NumberAxis)plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true); //自动生成
        rangeAxis.setUpperMargin(0.20);
        rangeAxis.setLabelAngle(Math.PI / 2.0);
        rangeAxis.setAutoRange(false);
        // 数据渲染部分 主要是对折线做操作
        LineAndShapeRenderer renderer=(LineAndShapeRenderer)plot.getRenderer();
        renderer.setBaseItemLabelsVisible(true);
        renderer.setSeriesPaint(0, Color.black);    //设置折线的颜色
        renderer.setBaseShapesFilled(true);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelFont(new Font("Dialog", 1, 14));  //设置提示折点数据形状
        plot.setRenderer(renderer);
        try{

            // 创建文件输出流
            File fos_jpg = new File("/home/fuji/tmp/"+name+".jpg");
            // 输出到哪个输出流
            ChartUtilities.saveChartAsJPEG(fos_jpg, chart, // 统计图表对象
                    700, // 宽
                    500 // 高
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String name;
    private Map<Long,Double> dataMap;
}
