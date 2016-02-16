package com.it.ibm.watson.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Utils {

	/**
	 * This method returns an InputStream, which is implemented by a zipped
	 * stream built from a directory. <br>
	 * 
	 * @param dirName
	 *            the name of the directory containing files to be zipped
	 * @return the zipped InputStream
	 */
	public static InputStream getCompressedStream(File dirName)
			throws IOException {

		if (!dirName.isDirectory())
			return null;

		byte data[] = new byte[2048];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		ZipOutputStream zos = new ZipOutputStream(bos);

		for (File f : dirName.listFiles()) {

			BufferedInputStream entryStream = new BufferedInputStream(
					new FileInputStream(f), 2048);
			ZipEntry entry = new ZipEntry(f.getName());
			zos.putNextEntry(entry);
			int count;
			while ((count = entryStream.read(data, 0, 2048)) != -1) {
				zos.write(data, 0, count);
			}
			entryStream.close();
			zos.closeEntry();

		}

		zos.close();

		return new ByteArrayInputStream(bos.toByteArray());
	}

}
