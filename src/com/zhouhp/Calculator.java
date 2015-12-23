package com.zhouhp;

import java.util.Date;

/**
 * Created by fuji on 15-12-21.
 */
public class Calculator {

    /**
	 * 计算地球上任意两点(经纬度)距离
	 *
	 * @param lonA
	 *            第一点经度
	 * @param latA
	 *            第一点纬度
	 * @param lonB
	 *            第二点经度
	 * @param latB
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 */
	public static double calDistance(double lonA, double latA, double lonB, double latB) {
		double radLatA = Math.toRadians(latA);
		double radLatB = Math.toRadians(latB);
		double a = radLatA - radLatB;
		double b = Math.toRadians(lonA) - Math.toRadians(lonB);

		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
				Math.cos(radLatA)*Math.cos(radLatB)*Math.pow(Math.sin(b/2),2)));

		s = s * EARTH_RADIUS;
		//s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	* @return 返回时间 单位:分钟
	*/
	public static long calTime(Date startDate,Date endDate){
		long minute=endDate.getTime()-startDate.getTime();

		return minute/(1000*60);

	}


	private static final double EARTH_RADIUS= 6378137; // 地球半径

}
