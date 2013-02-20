package com.deimos.adam.dto;

/**
*
* @author CARM
*/
public class VertexDto {

private int Id;
private String Latitude;
private String Longitude;

public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getLatitude() {
	return Latitude;
}
public void setLatitude(String latitude) {
	Latitude = latitude;
}
public String getLongitude() {
	return Longitude;
}
public void setLongitude(String longitude) {
	Longitude = longitude;
}

}