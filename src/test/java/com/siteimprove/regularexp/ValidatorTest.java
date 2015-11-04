package com.siteimprove.regularexp;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import com.siteimprove.regularexp.utils.Validator;

public class ValidatorTest {

	@Spy
	private Validator validator;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testNotValidFilePath(){
		Exception ex = null;
		try{
			validator.validateFilePath("");
		} catch (Exception exc){
			ex = exc;
		}
		assertNotNull(ex);
	}
	
	@Test
	public void testNotValidRegex(){
		Exception ex = null;
		try{
			validator.validateRegex(null);
		} catch (Exception exc){
			ex = exc;
		}
		assertNotNull(ex);
	}
	
	@Test
	public void testNotValidArgs(){
		Exception ex = null;
		try{
			validator.validateArguments(new String[]{});
		} catch (Exception exc){
			ex = exc;
		}
		assertNotNull(ex);
	}
}
