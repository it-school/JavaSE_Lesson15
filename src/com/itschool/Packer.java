package com.itschool;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Collection;

public class Packer {
   private static String getEntryName(File source, File file) throws IOException {
      int index = source.getAbsolutePath().length() + 1;
      String path = file.getCanonicalPath();

      return path.substring(index);
   }

   public static void pack(File source, File destination) throws IOException, ArchiveException {
      OutputStream archiveStream = new FileOutputStream(destination);
      ArchiveOutputStream archive = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP, archiveStream);

      Collection<File> fileList = FileUtils.listFiles(source, null, true);

      for (File file : fileList) {
         String entryName = getEntryName(source, file);
         ZipArchiveEntry entry = new ZipArchiveEntry(entryName);
         archive.putArchiveEntry(entry);

         BufferedInputStream input = new BufferedInputStream(new FileInputStream(file));

         IOUtils.copy(input, archive);
         input.close();
         archive.closeArchiveEntry();
      }

      archive.finish();
      archiveStream.close();
   }
}
