package com.siteimprove.regularexp.matcher;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.siteimprove.regularexp.exception.FilePatternMatcherException;

/**
 * Title: PatternMatcher
 *
 * Description: This class create a pool of maximum 10 threads. A number of FilePatternMatcherProcessors are assigned to the pool depending on the number of sub-set of
 * files.
 *
 * Copyright: Copyright (c) 2015
 *
 *
 * @author: Esau Castillo
 * @version: 1.0
 * @date: November 2, 2015
 *
 */
public class PatternMatcher {
	private String regex;
	private String filePath;
	private static int MAX_NUM_THREADS = 10;
	
	public PatternMatcher(String filePath, String regex) {
		this.filePath = filePath;
		this.regex = regex; 
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFileFolderName(String filePath) {
		this.filePath = filePath;
	}
	
	public void findPatterns() throws FilePatternMatcherException{
		executeThreads(new File(filePath));
	}
	
	 /**
 	 * This method receives a file/folder containing a set of input files. The pool of threads in this method.
 	 * The total number of files is split into sub-lists, the splitting process is done considering the maximum number of threads in the pool and
 	 * the total number of files to process. Once the sub-lists are created, a FilePatternMatcherProcessor is created per each sub-list and it is assigned to the
 	 * pool so that an available thread can execute the matching.
 	 * 
 	 * 
 	 * @param  file, the file/folder 
 	 * @throws FilePatternMatcherException
 	 */
	private void executeThreads(File dir) throws FilePatternMatcherException {
		ExecutorService threadPool = Executors.newFixedThreadPool(MAX_NUM_THREADS);
		for (List<File> files: getListOfFiles(dir)){
			Future<Map<String, List<Long>>> future = threadPool.submit(new FilePatternMatcherProcessor(files, regex));
			try {
				printMatchedLines(future.get());
			} catch (InterruptedException exc) {
				throw new FilePatternMatcherException(getClass().getName(), "Error while reading the result of the pattern matching.", exc);
			} catch (ExecutionException exc) {
				//TODO REMOVE
				throw new FilePatternMatcherException(getClass().getName(), "Error while reading the result of the pattern matching.", exc);
			}
		}
		threadPool.shutdown();
	}
	
	private void printMatchedLines(Map<String, List<Long>> matchedLines){
		for(String key: matchedLines.keySet()){
			System.out.println("File: " + key + " Number of line(s): " + matchedLines.get(key).size()+ " Line(s): " + Arrays.toString(matchedLines.get(key).toArray()));
		}
	}

	
	private List<List<File>> getListOfFiles(File file){
		List<List<File>> list = new ArrayList<List<File>>();
		if (file.exists() && !file.isDirectory()){
			list.add(new ArrayList<File>(Arrays.asList(file)));
		} else if (file.exists() && file.isDirectory()){
			list = getListOfFilesFromDir(file);
		}
		return list;
	}
	
	private List<List<File>> getListOfFilesFromDir(File dir){
		List<List<File>> list = new ArrayList<List<File>>();
		int numberOfFilesInDir = dir.listFiles().length;
		for (int i = 0; i < numberOfFilesInDir; i++) {
			List<File> subList;
			if (list.size() <= i%(MAX_NUM_THREADS * 2)) {
				subList = new ArrayList<File>();
				list.add(subList);
			} else {
				subList = list.get(i%(MAX_NUM_THREADS * 2));
			}
			subList.add(dir.listFiles()[i]);
		}
		return list;
	}
}
