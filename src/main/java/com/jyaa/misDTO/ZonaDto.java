package com.jyaa.misDTO;

public class ZonaDto {
	
	private Long id;
	private String barrio;
	private Float desdeLat;
	private Float hastaLat;
	private Float desdeLong;
	private Float hastaLong;
	
	public ZonaDto() {
		
	}

	public ZonaDto(Long id, String barrio, Float desdeLat, Float hastaLat, Float desdeLong, Float hastaLong) {
		this.id = id;
		this.barrio = barrio;
		this.desdeLat = desdeLat;
		this.hastaLat = hastaLat;
		this.desdeLong = desdeLong;
		this.hastaLong = hastaLong;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public Float getDesdeLat() {
		return desdeLat;
	}

	public void setDesdeLat(Float desdeLat) {
		this.desdeLat = desdeLat;
	}

	public Float getHastaLat() {
		return hastaLat;
	}

	public void setHastaLat(Float hastaLat) {
		this.hastaLat = hastaLat;
	}

	public Float getDesdeLong() {
		return desdeLong;
	}

	public void setDesdeLong(Float desdeLong) {
		this.desdeLong = desdeLong;
	}

	public Float getHastaLong() {
		return hastaLong;
	}

	public void setHastaLong(Float hastaLong) {
		this.hastaLong = hastaLong;
	}

}
