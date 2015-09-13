package com.moderndrummer.files;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moderndrummer.entity.exceptions.FileUploaderException;
import com.moderndrummer.enums.GraphicType;
import com.moderndrummer.messages.ModernDrummerMessages;
import com.moderndrummer.model.Memberblogpostimage;
import com.moderndrummer.web.components.WebComponentsParameters;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Component("fileUploader")
public class FileUploader {

	private static final Logger LOGGER = LoggerFactory.getLogger("errorslog");

	private File tmpDir;
	private final List<String> uploadedFileNames = new ArrayList<String>();
	private static final String MEMBERBLOG_GROUP = "memberBLogGroup";
	private final List<Memberblogpostimage> memberBlogGraphics = new ArrayList<Memberblogpostimage>();

	@Autowired
	FileUploadWriter fileUploadWriter;

	public void init() throws FileUploaderException {
		tmpDir = new File(fileUploadWriter.getFileDir());
		if (!tmpDir.isDirectory()) {
			final boolean created = tmpDir.mkdir();
			if (!created) {
				throw new FileUploaderException(WebComponentsParameters.TMP_DIR_PATH + " directory not created");
			}
		}

	}

	public DiskFileItemFactory setFileItemFactory(final HttpServletRequest request) {
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		if (fileItemFactory.getSizeThreshold() > 0) {
			fileItemFactory = newDiskFileItemFactory(request.getServletContext(), tmpDir, FileConstants.SIZE_TRESHOLD);
		}
		return fileItemFactory;
	}

	public DiskFileItemFactory newDiskFileItemFactory(final ServletContext context, final File repository,
			final int size) {
		if (context instanceof javax.servlet.ServletContext) {

			final DiskFileItemFactory factory = new DiskFileItemFactory(size, repository);
			return factory;
		} else {
			final DiskFileItemFactory factory = new DiskFileItemFactory(size, repository);
			return factory;
		}

	}

	public void readRequestParameters(final HttpServletRequest request, final Map<String, String> parameters,
			final List<Integer> listItems, final DiskFileItemFactory fileItemFactory, GraphicType graphicType)
					throws FileUploadException, Exception {
		try {
			fileItemFactory.setSizeThreshold(FileConstants.SIZE_TRESHOLD);
			fileItemFactory.setRepository(tmpDir);
			final ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
			final List<FileItem> items = uploadHandler.parseRequest(request);
			if (graphicType == GraphicType.BLOG_IMAGE) {
				final List<Memberblogpostimage> graphics = new ArrayList<Memberblogpostimage>();

				if (!items.isEmpty()) {
					for (final FileItem item : items) {
						if (item.isFormField()) {
							final String value = item.getString();
							final String parameterName = item.getFieldName();
							addParameters(parameters, listItems, value, parameterName);

						} else if (item.getSize() > 0) {
							Memberblogpostimage newPostImage = addGraphics(item, GraphicType.BLOG_IMAGE);
							if (newPostImage != null) {
								graphics.add(newPostImage);
							}
						}
					}

					if (!graphics.isEmpty()) {
						setMemberBlogGraphics(graphics);
					}
				}

			}

		} catch (final FileUploaderException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private void addParameters(final Map<String, String> parameters, final List<Integer> blogList, final String value,
			final String parameterName) {
		try {

			parameters.put(parameterName, value);

		} catch (final IllegalArgumentException e) {
			LOGGER.error("invalid argument fileuploader#addParameters " + e.getMessage());
		}

	}

	private <E> E addGraphics(final FileItem item, GraphicType graphicType) throws FileUploaderException {
		if (item != null && !StringUtils.isEmpty(item.getName()) && FileUtils.isValidFileName(item.getName())) {

			if (graphicType.equals(GraphicType.BLOG_IMAGE)) {
				return (E) addBlogItems(item);
			}

		} else if (!FileUtils.isValidFileName(item.getName()) && !item.getName().trim().isEmpty()) {
			throw new FileUploaderException("file corrupt");
		}

		throw new FileUploaderException("error loading graphics");

	}

	private synchronized Memberblogpostimage addBlogItems(final FileItem item) throws FileUploaderException {

		try {

			final File file = writeToFile(item);
			uploadedFileNames.add(item.getName());
			return setBlogPostImageFromFileItem(item, file);

		} catch (final Exception e) {
			LOGGER.error(ModernDrummerMessages.FILE_WRITE_ERROR, e);
			throw new FileUploaderException(ModernDrummerMessages.FILE_WRITE_ERROR);
		}

	}

	private File writeToFile(final FileItem item) throws Exception {
		final File file = new File(fileUploadWriter.getFileDir(), item.getName());
		item.write(file);
		file.setReadable(true);
		return file;
	}

	private synchronized Memberblogpostimage setBlogPostImageFromFileItem(final FileItem item, final File file) {
		// make insert to DiveLogGraphic
		final Memberblogpostimage graphics = new Memberblogpostimage();
		graphics.setFileName(item.getName());
		return graphics;
	}

	public void setMemberBlogGraphics(final List<Memberblogpostimage> graphics) {
		synchronized (memberBlogGraphics) {
			if (!memberBlogGraphics.isEmpty()) {
				memberBlogGraphics.clear();
			}
			if (!graphics.isEmpty()) {
				memberBlogGraphics.addAll(graphics);
			}
		}

	}

	public synchronized void emptyMemberBlogImages() {
		this.memberBlogGraphics.clear();
	}

	public synchronized List<Memberblogpostimage> getBlogGraphics() {
		return Collections.synchronizedList(memberBlogGraphics);
	}
}
