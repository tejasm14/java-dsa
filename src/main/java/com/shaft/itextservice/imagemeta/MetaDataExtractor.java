package com.shaft.itextservice.imagemeta;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class MetaDataExtractor {

    public static void main(String[] args) {

        File file = new File("C:\\Users\\tejas.mohite\\Downloads\\sample_jpeg.jpeg");
        Metadata metadata = null;
        try {
            metadata = ImageMetadataReader.readMetadata(file);

            for (Directory directory : metadata.getDirectories()) {

                directory.getTags().forEach(System.out::println);
                //System.out.println(directory.getString(1));
            }


            ExifSubIFDDirectory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (directory != null) {
                String date = directory.getString(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                System.out.println("Date Taken: " + date);
            }
        } catch (ImageProcessingException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
