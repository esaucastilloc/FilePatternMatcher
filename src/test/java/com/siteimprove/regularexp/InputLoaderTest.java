package com.siteimprove.regularexp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertNotNull;
import com.siteimprove.regularexp.utils.InputLoader;


public class InputLoaderTest {

	@Spy
	private InputLoader inputLoader;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testNotValidFilePath(){
		Exception ex = null;
		try{
			inputLoader.loadPropValues(new String[]{"Not valid file path"});
		} catch (Exception exc){
			ex = exc;
		}
		assertNotNull(ex);
	}
}
