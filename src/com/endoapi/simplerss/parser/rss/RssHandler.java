package com.endoapi.simplerss.parser.rss;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import com.endoapi.simplerss.parser.model.Message;

public class RssHandler extends DefaultHandler {

	
	
	
	private List<Message> messages;
	private Message currentMessage;
	private StringBuffer buffer = new StringBuffer();
	
	
	
	@Override
	public void startDocument() throws SAXException {
		
		super.startDocument();
		messages = new ArrayList<Message>();
		
		
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		super.startElement(uri, localName, qName, attributes);
		
		if(qName.equalsIgnoreCase(Message.ITEM)){
			this.currentMessage = new Message();
		}else if( qName.equalsIgnoreCase(Message.TITLE)){
			buffer.setLength(0);
		}else if( qName.equalsIgnoreCase(Message.LINK)){
			buffer.setLength(0);
		}else if(qName.equalsIgnoreCase(Message.PUBDATE)){
			buffer.setLength(0);
		}else if (qName.equalsIgnoreCase(Message.ITEM)){
			buffer.setLength(0);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		super.characters(ch, start, length);
		buffer.append(ch, start, length);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		super.endElement(uri, localName, qName);
		
		if(currentMessage!= null){
			if( qName.equalsIgnoreCase(Message.TITLE)){
				currentMessage.setTitle(buffer.toString());
			}else if( qName.equalsIgnoreCase(Message.LINK)){
				currentMessage.setLink(buffer.toString());
			}else if(qName.equalsIgnoreCase(Message.PUBDATE)){
				currentMessage.setPubDate(buffer.toString());
			}else if (qName.equalsIgnoreCase(Message.ITEM)){
				messages.add(currentMessage);
			}			
		}		
		
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
}
