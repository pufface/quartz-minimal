package sk.fillo.quartz;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Urls {
	private static final String RES_URL_HELLO = "java:comp/env/url/hello";

	public static String getUrl() {
		try {
			return InitialContext.doLookup(RES_URL_HELLO).toString();
		} catch (NamingException e) {
			e.printStackTrace();
			return "Naming exception " + e.toString();
		}
	}

}
