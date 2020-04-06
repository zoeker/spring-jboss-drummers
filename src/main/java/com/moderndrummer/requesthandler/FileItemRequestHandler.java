package com.moderndrummer.requesthandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moderndrummer.enums.GraphicType;
import com.moderndrummer.files.FileUploader;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Component
public class FileItemRequestHandler<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger("errorslog");

    @Autowired
    protected FileUploader fileUploader;

    public List<E> listGraphics = null;

    public List<E> getListGraphics() {
        return listGraphics;
    }

    public void clearListGraphics() {
        if (listGraphics != null) {
            listGraphics.clear();
        }
    }

    public Map<String, String> getRequestParameters(final HttpServletRequest request, GraphicType graphicType) {
        final Map<String, String> parameters = new HashMap<String, String>();
        synchronized (parameters) {
            try {
                final DiskFileItemFactory fileItemFactory = fileUploader.setFileItemFactory(request);
                fileUploader.readRequestParameters(request, parameters, new ArrayList<Integer>(), fileItemFactory,
                        graphicType);
                if (graphicType == GraphicType.BLOG_IMAGE) {
                    listGraphics = (List<E>) fileUploader.getBlogGraphics();
                }
            } catch (final FileUploadException | IOException ex) {
                log("Error encountered while parsing the request", ex);
            } catch (final Exception ex) {
                log("Error encountered while uploading file", ex);
            }

            return parameters;
        }

    }

    private void log(String message, Exception e) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(message, e);
        }

    }

}
