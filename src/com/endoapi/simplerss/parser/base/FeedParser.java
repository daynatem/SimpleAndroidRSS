package com.endoapi.simplerss.parser.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.xml.sax.InputSource;

import com.endoapi.simplerss.parser.model.Message;

public abstract class FeedParser implements FeedParserBase {


	
	private String urlRss;
	
	@Override
	public abstract void parse();

	
	public FeedParser (String urlRss){
		this.urlRss = urlRss;		
	}
	
	public InputSource getInputstream (	){
		
		try {
			
			InputStream stream = new URL(urlRss).openConnection().getInputStream();
		
			InputSource source = new InputSource(stream);
			
			source.setEncoding("UTF-8");
			
			
			return source;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return null;
		
	}


	public abstract List<Message> getMessages();
}
