package com.org.ticketzone.app_mem.vo;

import android.util.Log;

import lombok.Data;

@Data
public class StoreVO {
	private String license_number;
	private String r_name;
	private String zipcode;
	private String max_number;
	private int store_status;
	private String cate_code;
	private String owner_id;
	private String store_name;
	private String store_tel;
	private String store_time;
	private String address_name;
	private String store_intro;
	private String img_uuid;
	private String img_uploadpath;
	private String img_filename;
	private String sido;
	private String sigoon;
	private String time;
	private String time2;

	private String coor_x; // 경도
	private String coor_y; // 위도
	private double distance;

	public void setDistance(double my_x, double my_y) {

		double x = Double.parseDouble(this.coor_x);
		double y = Double.parseDouble(this.coor_y);
		double theta = my_x - x;
		double dist = Math.sin(deg2rad(my_y)) * Math.sin(deg2rad(y)) + Math.cos(deg2rad(my_y))
				* Math.cos(deg2rad(y)) * Math.cos(deg2rad(theta));

		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		dist = dist * 1000.0;
		this.distance = dist;
		Log.e("dist", dist+"km");
//      this.distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))+"";
	}
	private double deg2rad(double deg) {
		return (deg * Math.PI / 180d);
	}

	// This function converts radians to decimsl degrees
	private double rad2deg(double rad) {
		return (rad * 180d / Math.PI);
	}
}