package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.junit.Test;

public class test {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(test.class);

	@Test
	public void main() {
		// TODO Auto-generated method stub
		String a = "12asdxr234ds325412";

		String regex = "\\b12(.*)12\\b";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(a);
		while (m.find()) {
			String val = m.group(1);
			logger.info(val);
		}

	}
}
