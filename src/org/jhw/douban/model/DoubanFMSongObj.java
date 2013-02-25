package org.jhw.douban.model;

/**
 * 豆瓣 json 数据格式示例
{
    "r": 0,
    "song": [
        {
            "album": "/subject/20279479/",
            "picture": "http://img3.douban.com/mpic/s23129754.jpg",
            "ssid": "6e5c",
            "artist": "FIDLAR",
            "url": "http://mr5.douban.com/201302162056/3d9d883534f0272803d0003de8d2f7c8/view/song/small/p1889861.mp3",
            "company": "Mom + Pop",
            "title": "No Waves",
            "rating_avg": 3.88172,
            "length": 134,
            "subtype": "",
            "public_time": "2013",
            "sid": "1889861",
            "aid": "20279479",
            "kbps": "64",
            "albumtitle": "Fidlar",
            "like": false
        },
        ...
    ]
}
 * 
 * @author jhw
 *
 */

public class DoubanFMSongObj {

	private String album;
	
	private String picture;
	
	private String ssid;
	
	private String artist;
	
	private String url;
	
	private String company;
	
	private String title;
	
	private String rating_avg;
	
	private String length;
	
	private String subtype;
	
	private String public_time;
	
	private String sid;
	
	private String aid;
	
	private String kbps;
	
	private String albumtitle;
	
	private String like;

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRating_avg() {
		return rating_avg;
	}

	public void setRating_avg(String rating_avg) {
		this.rating_avg = rating_avg;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getPublic_time() {
		return public_time;
	}

	public void setPublic_time(String public_time) {
		this.public_time = public_time;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getKbps() {
		return kbps;
	}

	public void setKbps(String kbps) {
		this.kbps = kbps;
	}

	public String getAlbumtitle() {
		return albumtitle;
	}

	public void setAlbumtitle(String albumtitle) {
		this.albumtitle = albumtitle;
	}

	public String isLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	@Override
	public String toString() {
		return "DoubanFM [album=" + album + ", picture=" + picture + ", ssid="
				+ ssid + ", artist=" + artist + ", url=" + url + ", company="
				+ company + ", title=" + title + ", rating_avg=" + rating_avg
				+ ", length=" + length + ", subtype=" + subtype
				+ ", public_time=" + public_time + ", sid=" + sid + ", aid="
				+ aid + ", kbps=" + kbps + ", albumtitle=" + albumtitle
				+ ", like=" + like + "]";
	}
}
