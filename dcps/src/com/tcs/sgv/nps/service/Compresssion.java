package com.tcs.sgv.nps.service;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.stream.*;

public class Compresssion {

	public static void main(String[] args) throws IOException {

	    File input = new File("D:/outputnps/images/06DHEMHSM8202_o_photo.jpg");
	    BufferedImage image = ImageIO.read(input);

	    File compressedImageFile = new File("D:/outputnps/images/compressed_image.jpg");
	    OutputStream os = new FileOutputStream(compressedImageFile);

	    Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
	    ImageWriter writer = (ImageWriter) writers.next();

	    ImageOutputStream ios = ImageIO.createImageOutputStream(os);
	    writer.setOutput(ios);

	    ImageWriteParam param = writer.getDefaultWriteParam();

	    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
	    param.setCompressionQuality(0.05f);  // Change the quality value you prefer
	    writer.write(null, new IIOImage(image, null, null), param);

	    os.close();
	    ios.close();
	    writer.dispose();

}
}
