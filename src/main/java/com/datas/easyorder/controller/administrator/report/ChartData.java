package com.datas.easyorder.controller.administrator.report;

public class ChartData {

	private String x;
	private String lineName;
	private String reportName;
	
	private Object[] y;

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public Object[] getY() {
		return y;
	}

	public void setY(Object[] y) {
		this.y = y;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	
	
	
}
