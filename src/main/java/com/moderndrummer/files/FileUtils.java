package com.moderndrummer.files;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
import org.apache.commons.io.FilenameUtils;

import com.moderndrummer.entity.exceptions.ModernDrummerException;
import com.moderndrummer.exceptions.ModernDrummerInvalidFormatException;
import com.moderndrummer.messages.ModernDrummerMessages;
import com.moderndrummer.util.ObjectUtil;
import com.moderndrummer.validators.StringUtilValidator;
import com.moderndrummer.web.components.WebComponentsParameters;

public class FileUtils {

	public static String getExtension(final String fileName)
			throws ModernDrummerInvalidFormatException, ModernDrummerException {
		if (StringUtilValidator.isEmptyOrNull(fileName)) {
			throw new ModernDrummerInvalidFormatException(ModernDrummerMessages.FILE_ERROR);
		}
		final String trimmedFileName = fileName.trim();
		final int index = trimmedFileName.lastIndexOf('.');
		if (index == -1) {
			throw new ModernDrummerException(ModernDrummerMessages.FILE_INVALID);
		} else {
			return trimmedFileName.substring(index + 1, trimmedFileName.length());
		}
	}

	public static boolean isValidExtension(final String fileName, final FileTypes type) {
		if (getExtension(fileName).equals(type.getFileTypeExtension())) {
			return true;
		}

		return false;
	}

	public static boolean isValidFileName(final String fileName) {
		if (StringUtilValidator.isEmptyOrNull(fileName)) {
			return false;
		}
		if (StringUtilValidator.isEmptyOrNull(FilenameUtils.getExtension(fileName))) {
			return false;
		}

		return true;

	}

	public static boolean isValidYouTubeUrl(final String url) {
		if (StringUtilValidator.isEmptyOrNull(url)) {
			return false;
		}
		if (!org.apache.commons.lang3.StringUtils.startsWith(url, WebComponentsParameters.YOU_TUBE_URL)
				&& !org.apache.commons.lang3.StringUtils.startsWith(url, WebComponentsParameters.YOU_TUBE_URL_EMBED)) {
			return false;
		}

		return true;

	}

	public static String renameFileName(final String name, final int counter)
			throws ModernDrummerInvalidFormatException, ModernDrummerException {

		if (StringUtilValidator.isEmptyOrNull(name)) {
			throw new ModernDrummerInvalidFormatException(ModernDrummerMessages.FILE_ERROR);
		}

		final String extension = FilenameUtils.getExtension(name);
		if (!ObjectUtil.noNullElements(extension)) {
			throw new ModernDrummerInvalidFormatException(ModernDrummerMessages.FILE_ERROR);
		}

		final int indexExtension = FilenameUtils.indexOfExtension(name);
		final String newFileName = name.substring(0, indexExtension) + "_" + String.valueOf(counter) + "." + extension;

		return newFileName;

	}

	public static String replaceColon(final String fileName) {
		final String newValue = fileName.replace(':', '_');
		return newValue;
	}

	public static String replaceTimeFormat(final String fileName) {
		final String newValue = replaceColon(replaceWhiteSpace(fileName));
		return newValue;
	}

	public static String replaceWhiteSpace(final String fileName) {
		final String newValue = fileName.replace(' ', '_');
		return newValue;
	}

}
