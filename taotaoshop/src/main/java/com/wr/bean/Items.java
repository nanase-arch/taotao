package com.wr.bean;

import java.util.Date;

public class Items {
private int id;
private String title;
private String sellPoint;
private int price;
private int num;
private String barcode;
private String image;
private int cId;
private byte status;
private Date created;
private Date updated;
@Override
public String toString() {
	return "Items [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price + ", num=" + num
			+ ", barcode=" + barcode + ", image=" + image + ", cId=" + cId + ", status=" + status + ", created="
			+ created + ", updated=" + updated + "]";
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getSellPoint() {
	return sellPoint;
}
public void setSellPoint(String sellPoint) {
	this.sellPoint = sellPoint;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public String getBarcode() {
	return barcode;
}
public void setBarcode(String barcode) {
	this.barcode = barcode;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public int getcId() {
	return cId;
}
public void setcId(int cId) {
	this.cId = cId;
}
public byte getStatus() {
	return status;
}
public void setStatus(byte status) {
	this.status = status;
}
public Date getCreated() {
	return created;
}
public void setCreated(Date created) {
	this.created = created;
}
public Date getUpdated() {
	return updated;
}
public void setUpdated(Date updated) {
	this.updated = updated;
}
}
