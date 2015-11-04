package com.siteimprove.regularexp.matcher;


import com.siteimprove.regularexp.exception.FilePatternMatcherException;
import com.siteimprove.regularexp.matcher.PatternMatcher;
import com.siteimprove.regularexp.utils.InputLoader;
import com.siteimprove.regularexp.utils.Validator;


public class FilePatternMatcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Validator validator  = new Validator();
		try{
			validator.validateArguments(args);
			InputLoader loader = new InputLoader();
			loader.loadPropValues(args);
			validator.validateRegex(loader.getRegex());
			validator.validateFilePath(loader.getFilePath());
			PatternMatcher matcher = new PatternMatcher(loader.getFilePath(), loader.getRegex());
			matcher.findPatterns();	
		} catch (FilePatternMatcherException exc){
			System.out.println(exc.getMessage());
		} 

	}

}
