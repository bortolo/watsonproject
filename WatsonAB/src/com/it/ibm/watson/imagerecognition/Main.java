package com.it.ibm.watson.imagerecognition;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.ibm.watson.developer_cloud.visual_recognition.v2.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v2.model.VisualClassification;
import com.it.ibm.watson.utils.Utils;

// documentazione
// https://github.com/watson-developer-cloud/java-sdk
// http://www.ibm.com/smarterplanet/us/en/ibmwatson/developercloud/doc/visual-recognition/
// https://visual-recognition-demo.mybluemix.net/?cm_mc_uid=78577515283814518562637&cm_mc_sid_50200000=1452107285
// http://www.ibm.com/smarterplanet/us/en/ibmwatson/developercloud/visual-recognition/api/v2/


// XXX altra cosa che vorrei usare: ermette anche di inviare / ricevere.. anche notifiche
//https://core.telegram.org/bots

/**
 * Classe di test per provare Watson SDK
 * @author Marco Dondio
 *
 */
public class Main {

	public static void main(String[] args) throws MalformedURLException,
			IOException {


		// First, we instantiate the service.. we need to setup on bluemix first and obtain credentials
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2015_12_02);
		service.setUsernameAndPassword(ImageRecognitionConfig.username, ImageRecognitionConfig.password);

		// Use single image
//		File image = new File("1.JPG");
//		VisualClassification recognizedImage = service.classify(image);


		// Use zipped file
//		File images = new File("images.zip");
//		VisualClassification recognizedImage = service.classify(images);

		// Compress images on the fly
		VisualClassification recognizedImage = service.classify("imgs.zip", Utils.getCompressedStream(new File("imageDir")));
		System.out.println(recognizedImage);
	}
	
	
//	 public static void main(String[] args) {
//		    VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2015_12_02);
//		    service.setUsernameAndPassword("<username>", "<password>");
//
//		    File image = new File("src/test/resources/visual_recognition/car.png");
//
//		    System.out.println("Classify using all the classifiers");
//		    VisualClassification result = service.classify(image);
//		    System.out.println(result);
//
//		    System.out.println("Classify using the 'Car' classifier");
//		    VisualClassifier car = new VisualClassifier("Car");
//		    result = service.classify(image, car);
//		    System.out.println(result);
//
//		    System.out.println("Create a classifier with positive and negative images");
//
//		    File positiveImages = new File("src/test/resources/visual_recognition/positive.zip");
//		    File negativeImages = new File("src/test/resources/visual_recognition/negative.zip");
//
//		    VisualClassifier foo = service.createClassifier("foo", positiveImages, negativeImages);
//		    System.out.println(foo);
//
//
//		  }


}
