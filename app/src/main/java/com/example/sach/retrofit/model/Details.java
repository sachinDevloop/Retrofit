package com.example.sach.retrofit.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Details {

	@Expose
	private List<Stocks> data;

	private String u_name;


	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_name() {
		return u_name;
	}

	public List<Stocks> getData() {
		return data;
	}

	public void setData(List<Stocks> data) {
		this.data = data;
	}
}
