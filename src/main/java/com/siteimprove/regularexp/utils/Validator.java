package com.siteimprove.regularexp.utils;

import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.siteimprove.regularexp.exception.FilePatternMatcherException;

public class Validator {

	
	public void validateArguments(String[] args) throws FilePatternMatcherException{
		if ((args == null) || (args != null && args.length < 1)) {
			throw new FilePatternMatcherException(getClass().getName(), "Invalid number of arguments: [input fileName]");
		}
	}

	
	public void validateRegex(String regex) throws FilePatternMatcherException{
		if(regex == null){
			throw new FilePatternMatcherException(getClass().getName(), "Empty regular expression. Please verify");
		}
		try{
			Pattern.compile(regex);
		} catch (PatternSyntaxException exc) {
			throw new FilePatternMatcherException(getClass().getName(), "Invalid regular expression. Please verify", exc);
		}		
	}
 
	public void validateFilePath(String filePath) throws FilePatternMatcherException {
		if(filePath == null || filePath == "") {
			throw new FilePatternMatcherException(getClass().getName(), "Empty file/folder path. Please verify");
		}
		File file =  new File(filePath);
		if (!file.exists()){
			throw new FilePatternMatcherException(getClass().getName(), "The file/folder path does not exists. Please verify");	
		}
	}
}
