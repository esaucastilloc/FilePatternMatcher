package com.siteimprove.regularexp.matcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.regex.Pattern;

import com.siteimprove.regularexp.exception.FilePatternMatcherException;
import com.siteimprove.regularexp.utils.Utils;
/**
 * Title: FilePatternMatcherProcessor
 *
 * Description: This class loops through a list of files and finds the matching lines for a given regular expression. The FilePatternMatcher processor
 * is executed by an available Thread.
 *
 * Copyright: Copyright (c) 2015
 *
 *
 * @author: Esau Castillo
 * @version: 1.0
 * @date: November 2, 2015
 *
 */
public class FilePatternMatcherProcessor implements Callable<Map<String, List<Long>>>{
	
	private List<File> fileList;
	private Map<String, List<Long>> matchedLines;
	private String regex;
	private Pattern pattern; 

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public FilePatternMatcherProcessor(List<File> fileList, String regex) {
		this.fileList = fileList;
		this.regex = regex;
	}
	
	public List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

	public Map<String, List<Long>> getMatchedLines(){
		if (matchedLines == null){
			matchedLines = new HashMap<String, List<Long>>();
		}
		return matchedLines;
	}

	public void setMatchedLines(Map<String, List<Long>> matchedLines){
		this.matchedLines = matchedLines;
	}

	private void findMatchingLines() throws FilePatternMatcherException {
		FileInputStream inputStream = null;
		Scanner sc = null;
		pattern = Pattern.compile("(\\d+):".concat(regex), Pattern.MULTILINE);
		for(File file: getFileList()){
			List<Long> lineNumberList = new ArrayList<Long>();
			try{
				inputStream = new FileInputStream(file);
			    sc = new Scanner(inputStream,  StandardCharsets.UTF_8.name());
			    while (sc.hasNextLine()) {
			        if(sc.findInLine(pattern) != null) {
			        	lineNumberList.add(new Long(sc.match().group(1)));
			        }
		        	sc.nextLine();
			    }
			    getMatchedLines().put(file.getName(), lineNumberList);
			} catch (FileNotFoundException exc) {
				throw new FilePatternMatcherException(getClass().getName(), "File not found: " + file.getName(), exc);
			} finally {
				Utils.close(inputStream);
				if (sc != null) sc.close();
			}
		}
	}

	 /**
 	 * The implementation of this method loops through a List of files and per each line of the files matches a regex. If the line
 	 * matches the regex then the line number is stored in a map. The content of the map represents all the lines matching grouped by file name. 
 	 *
 	 */
	public Map<String, List<Long>> call() throws Exception {
		findMatchingLines();
		return getMatchedLines();
	}

}
