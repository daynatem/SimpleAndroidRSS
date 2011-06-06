package com.endoapi.simplerss.parser.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class Message extends HashMap<String, String>{
	
	public static final String CHANNEL = "channel";
	public static final String PUBDATE = "pubDate";
	public static final String LINK = "link";
	public static final String ITEM = "item";
	public static final String TITLE = "title";
	public static final String USER = "user";
	
	private static DateFormat  formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
	
	private String	title;
	private String	link;
	private String	pubDate;
	private String 	user;
	
	
	
	
	public String getUser() {
		return get(USER);
	}
	public void setUser(String user) {
		this.user = user;
		put(USER, user);
	}

	public String getTitle() {
		return get(TITLE);
	}
	public void setTitle(String title) {
			this.title = title;
			try {
				this.setUser( title.substring(0, title.indexOf(':')));
				put(TITLE,title.substring(title.indexOf(':')+1) );
			} catch (Exception e) {
				System.err.println(title);
				e.printStackTrace();
			}
	
		
	}
	public String getLink() {
		return get(LINK);
	}
	public void setLink(String link) {
		this.link = link;
		put(LINK, link);
	}

	public void setPubDate(String dateStr){		
		this.pubDate = dateStr;	
		put(PUBDATE, dateStr);		
	}
	@Override
	public String toString() {
		return user + ' ' + title + ' ' + pubDate + ' ' + link; 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pubDate == null) ? 0 : pubDate.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
}
