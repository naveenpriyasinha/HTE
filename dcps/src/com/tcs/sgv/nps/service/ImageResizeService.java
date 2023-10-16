import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nsdl.craonlineutils.webservice.aadhar.form.ImageResizeRequestForm;
import com.nsdl.craonlineutils.webservice.aadhar.form.ImageResizeResponseForm;
import com.nsdl.craonlineutils.webservice.constants.WebServiceConstant;


@RestController
@RequestMapping(value ="/imageResizeService" )
public class ImageResizeServiceController implements WebServiceConstant {

	public static final Logger LOGGER = LoggerFactory.getLogger(ImageResizeServiceController.class);

	@RequestMapping(value = "/reSizeImage",	method = RequestMethod.POST)
	public ResponseEntity<ImageResizeResponseForm> getResizedImage(
			@RequestBody ImageResizeRequestForm imageResizeRequestForm) {
		
		HttpHeaders headers = new HttpHeaders();
		ImageResizeResponseForm imageResizeResponseForm = new ImageResizeResponseForm();
		imageResizeResponseForm.setAppName(imageResizeRequestForm.getAppName()!=null
				?imageResizeRequestForm.getAppName():"CROnline");
		LOGGER.info("This class will resize the image");
		
		Image img = null;
		BufferedImage tempJPG = null;
		ByteArrayOutputStream newFileJPG = new ByteArrayOutputStream();
		byte imageArray[]=Base64.decodeBase64(imageResizeRequestForm.getOriginalImageString());
		
		int fileLength=imageArray.length;
		if(fileLength<DEFAULT_RESIZE_IMAGE_LOWER_LIMIT){
			LOGGER.info("fileLength is less than required size {}",fileLength);
			imageResizeResponseForm.setErrMsg("FileLength is less than required size "+fileLength);
			imageResizeResponseForm.setResizeStatus(WEBSERVICE_FAILURE);
			headers.add("status",WEBSERVICE_FAILURE);
			headers.add("statusCode",WEBSERVICE_FAILURE_STSCODE);
			return new ResponseEntity<ImageResizeResponseForm>(imageResizeResponseForm, headers, HttpStatus.OK); 
		}
		if(fileLength>=DEFAULT_RESIZE_IMAGE_LOWER_LIMIT 
				&& fileLength<=DEFAULT_RESIZE_IMAGE_UPPER_LIMIT){
			LOGGER.info("fileLength is already in between 4 KB to 12 KB so no need to resize {}");
			imageResizeRequestForm.setOriginalImage(imageResizeRequestForm.getOriginalImage());
			imageResizeResponseForm.setOutputImage(imageResizeRequestForm.getOriginalImage());
			imageResizeResponseForm.setEncodedMsg(Base64.encodeBase64String(imageResizeRequestForm.getOriginalImage()));
			imageResizeResponseForm.setErrMsg(EMPTY_STRING);
			imageResizeResponseForm.setResizeStatus(WEBSERVICE_SUCCESS);
			imageResizeResponseForm.setEncodedMsg(Base64.encodeBase64String(imageArray));
			headers.add("status",WEBSERVICE_SUCCESS);
			headers.add("statusCode",WEBSERVICE_SUCCESS_STSCODE);
			return new ResponseEntity<ImageResizeResponseForm>(imageResizeResponseForm, headers, HttpStatus.OK); 
		}
		try {
			int width=DEFAULT_RESIZE_IMAGE_WIDTH;
			int height=DEFAULT_RESIZE_IMAGE_HEIGHT;
			LOGGER.info("Size of original byte array {}",imageArray.length);
			imageResizeRequestForm.setOriginalImage(imageResizeRequestForm.getOriginalImage());
			img = ImageIO.read(new ByteArrayInputStream(imageArray));
			LOGGER.info("After creating image object pass it to resizeImage function");
			tempJPG = resizeImage(img, DEFAULT_RESIZE_IMAGE_WIDTH, DEFAULT_RESIZE_IMAGE_HEIGHT);
			LOGGER.info("After call to resizeImage function");
			ImageIO.write(tempJPG, DEFAULT_RESIZE_IMAGE_FILE_TYPE, newFileJPG);
			while(newFileJPG.toByteArray().length>DEFAULT_RESIZE_IMAGE_UPPER_LIMIT){
				tempJPG = resizeImage(img, DEFAULT_RESIZE_IMAGE_WIDTH, DEFAULT_RESIZE_IMAGE_HEIGHT);
				ImageIO.write(tempJPG, DEFAULT_RESIZE_IMAGE_FILE_TYPE, newFileJPG);
			}
			if((newFileJPG.toByteArray().length<DEFAULT_RESIZE_IMAGE_LOWER_LIMIT)){
				while(newFileJPG.toByteArray().length<DEFAULT_RESIZE_IMAGE_LOWER_LIMIT 
						&& width<=400){
					width=width+RESIZE_IMAGE_WIDTH;
					height=height+RESIZE_IMAGE_HEIGHT;
					tempJPG = resizeImage(img, width, height);
					ImageIO.write(tempJPG, DEFAULT_RESIZE_IMAGE_FILE_TYPE, newFileJPG);
					if(newFileJPG.toByteArray().length>DEFAULT_RESIZE_IMAGE_LOWER_LIMIT
							&& newFileJPG.toByteArray().length < DEFAULT_RESIZE_IMAGE_UPPER_LIMIT){
						break;
					}
				}
				
			}
			imageResizeResponseForm.setOutputImage(newFileJPG.toByteArray());
			imageResizeResponseForm.setErrMsg(EMPTY_STRING);
			imageResizeResponseForm.setResizeStatus(WEBSERVICE_SUCCESS);
			headers.add("status",WEBSERVICE_SUCCESS);
			headers.add("statusCode",WEBSERVICE_SUCCESS_STSCODE);
			imageResizeResponseForm.setEncodedMsg(Base64.encodeBase64String(newFileJPG.toByteArray()));
			LOGGER.info("Size of new byte array {}",newFileJPG.toByteArray().length);
		} catch (Exception e) {
			LOGGER.error("Error Occured while resize the image Webservices {}",e);
			imageResizeResponseForm.setErrMsg("Exception occured during conversion: "+e.getMessage());
			imageResizeResponseForm.setResizeStatus(WEBSERVICE_FAILURE);
			headers.add("status",WEBSERVICE_FAILURE);
			headers.add("statusCode",WEBSERVICE_FAILURE_STSCODE);
		} 
		
		return new ResponseEntity<ImageResizeResponseForm>(imageResizeResponseForm, headers, HttpStatus.OK); 
	}
	
	/**
	 * This function resize the image file and returns the BufferedImage object that can be saved to file system.
	 */
	public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
	
	
}
