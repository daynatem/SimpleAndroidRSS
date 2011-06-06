package com.endoapi.simplerss.parser.rss;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.endoapi.simplerss.parser.base.FeedParser;
import com.endoapi.simplerss.parser.model.Message;

public class RssFeedParser extends FeedParser {

	
	private List<Message> messages;
	
	public RssFeedParser(String urlRss) {
		super(urlRss);		
	}

	@Override
	public void parse() {
		
		try {
			SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
			RssHandler handler = new RssHandler();
			parser.parse(this.getInputstream(), handler);
			messages = handler.getMessages();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	public static void main(String arg[]){
		
		
		
	}

	@Override
	public List<Message> getMessages() {
		return messages;
	}
	
}
