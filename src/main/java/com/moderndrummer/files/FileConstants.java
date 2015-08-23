package com.moderndrummer.files;

import com.moderndrummer.web.components.WebComponentsParameters;

/***
 * 
 * @author conpem 2015-08-03
 *
 */

public class FileConstants {

	public static final String PDF = ".pdf";
	public static final String XML = ".xml";
	public static final int SIZE_TRESHOLD = 1 * 1024 * 1024 * 500;
	public static final String homeDir = System.getProperty("jboss.home.dir");
	public static final String fileDir = homeDir + "/" + WebComponentsParameters.DESTINATION_DIR_PATH;

}
