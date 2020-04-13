package model;

import java.time.LocalDate;

public class Blog
{
	int blogid;
	String blogTitle;
	String blogDescrptn;
	LocalDate postedOn;
	
	
	public int getBlogid() {
		return blogid;
	}
	public void setBlogid(int blogid) {
		this.blogid = blogid;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogDescrptn() {
		return blogDescrptn;
	}
	public void setBlogDescrptn(String blogDescrptn) {
		this.blogDescrptn = blogDescrptn;
	}
	public LocalDate getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(LocalDate postedOn) {
		this.postedOn = postedOn;
	}
	
	
	public Blog(int blogid, String blogTitle, String blogDescrptn, LocalDate postedOn) {
		super();
		this.blogid = blogid;
		this.blogTitle = blogTitle;
		this.blogDescrptn = blogDescrptn;
		this.postedOn = postedOn;
	}
	
	
	
	
	
}
