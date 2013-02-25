package org.jhw.douban.model;

import java.util.List;

public class DoubanFMObj {
	
	private String r;
	private List<DoubanFMSongObj> song;
	
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public List<DoubanFMSongObj> getSong() {
		return song;
	}
	public void setSong(List<DoubanFMSongObj> song) {
		this.song = song;
	}
	
}
