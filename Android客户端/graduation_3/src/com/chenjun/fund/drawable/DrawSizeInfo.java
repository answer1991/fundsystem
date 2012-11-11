package com.chenjun.fund.drawable;

/**
 * 封装一个画板的尺寸信息，供画图时调用
 * @author zet
 *
 */
public class DrawSizeInfo {
	public DrawSizeInfo(){
		
	}
	public DrawSizeInfo(double width, double height, double paddingLeft, double paddingBottom, double paddingRight, double paddingUp){
		this.width = width;
		this.height = height;
		this.paddingLeft = paddingLeft;
		this.paddingBottom = paddingBottom;
		this.paddingRight = paddingRight;
		this.paddingUp = paddingUp;
	}
	private double width;
	private double height;
	private double paddingLeft;
	private double paddingRight;
	private double paddingUp;
	private double paddingBottom;
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getPaddingLeft() {
		return paddingLeft;
	}
	public void setPaddingLeft(double paddingLeft) {
		this.paddingLeft = paddingLeft;
	}
	public double getPaddingRight() {
		return paddingRight;
	}
	public void setPaddingRight(double paddingRight) {
		this.paddingRight = paddingRight;
	}
	public double getPaddingUp() {
		return paddingUp;
	}
	public void setPaddingUp(double paddingUp) {
		this.paddingUp = paddingUp;
	}
	public double getPaddingBottom() {
		return paddingBottom;
	}
	public void setPaddingBottom(double paddingBottom) {
		this.paddingBottom = paddingBottom;
	}
}
