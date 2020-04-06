package com.moderndrummer.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Component;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Component("fileUploadWriter")
public class FileUploadWriter {

    public String getFileDir() {
        return FileConstants.fileDir;
    }

    public String getFileName(final MultivaluedMap<String, String> header) {

        final String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (final String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {

                final String[] name = filename.split("=");

                final String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

    public String getHomeDir() {
        return FileConstants.homeDir;
    }

    public void writeFile(final byte[] content, final String filename) throws IOException {

        final File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        final FileOutputStream fop = new FileOutputStream(file);

        fop.write(content);
        fop.flush();
        fop.close();

    }

}
