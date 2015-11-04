package com.siteimprove.regularexp.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.siteimprove.regularexp.exception.FilePatternMatcherException;
/**
 * Title: InputLoader
 *
 * Description: Loads the input file/folder and the regular expression from an input-properties file.
 *
 * Copyright: Copyright (c) 2015
 *
 *
 * @author: Esau Castillo
 * @version: 1.0
 * @date: November 2, 2015
 *
 */
public class InputLoader {
	private InputStream inputStream;

	
	public String getFilePath() {
		return filePath;
	}

	public void setFileDir(String filePath) {
		this.filePath = filePath;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	private String filePath;
	private String regex;
	 
	public void loadPropValues(String args[]) throws FilePatternMatcherException {

		String fileName = args[0];
		try {		
 			inputStream = new FileInputStream(fileName);
 			Properties prop = new Properties();
 			prop.load(inputStream);
			filePath = prop.getProperty("file");
			regex = prop.getProperty("regex");
			 
		} catch(FileNotFoundException exc){
			throw new FilePatternMatcherException(getClass().getName(), "The input file '" + fileName + "' was not found", exc);
		} catch (IOException exc){
			throw new FilePatternMatcherException(getClass().getName(), "Unable to read the content of the filename:  '" + fileName, exc);
		}finally {
			Utils.close(inputStream);
		}
	}
}
