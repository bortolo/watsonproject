package com.it.ibm.watson.cvassesment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import com.ibm.watson.developer_cloud.document_conversion.v1.DocumentConversion;
import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;

// https://www.ibm.com/smarterplanet/us/en/ibmwatson/developercloud/doc/document-conversion/

// XXX capire Answer units are optimized for use with the Learning to Rank Service.

// per prefiltro: customizzazioni???? xpath!!!
// Download the sample custom JSON conversion file: tutorial-config.json. This JSON contains the customization parameters that can clean up a typical corporate webpage. 
//Using your own data
//To create your own custom JSON, see Advanced customization options.
// https://www.ibm.com/smarterplanet/us/en/ibmwatson/developercloud/doc/document-conversion/customizing.shtml

// personality insight doc
// https://www.ibm.com/smarterplanet/us/en/ibmwatson/developercloud/personality-insights/api/v2/
// https://www.ibm.com/smarterplanet/us/en/ibmwatson/developercloud/apis/#!/personality-insights/profile

// esempi src
// https://github.com/watson-developer-cloud/java-sdk/blob/master/examples/java/com/ibm/watson/developer_cloud/document_conversion/v1/DocumentConversionExample.java
// https://github.com/watson-developer-cloud/java-sdk/blob/master/examples/java/com/ibm/watson/developer_cloud/document_conversion/v1/DocumentConversionCustomConfigExample.java

/**
 * Classe di test per provare Watson SDK
 * 
 * @author Marco Dondio
 *
 */
public class Main {

	public static final String DOCSDIR = "CVs/";
	public static final String OUTDIR = "CVs_output/";

	public static void main(String[] args) throws MalformedURLException,
			IOException {

		// 1 - istanziare servizi, doc conversion, personality insight

		// First we obtain a txt from PDF
		System.out.println("Convert PDF document to Normalized Text...");

		DocumentConversion service = new DocumentConversion(
				DocumentConversion.VERSION_DATE_2015_12_01);
		service.setUsernameAndPassword(DocumentConversionConfig.usernameAndrea,
				DocumentConversionConfig.passwordAndrea);

		
		PersonalityInsights pi = new PersonalityInsights();
		pi.setUsernameAndPassword(PersonalityInsightConfig.usernameAndrea,
				PersonalityInsightConfig.passwordAndrea);


		
		// iterate on docs folder
		File dir = new File(DOCSDIR);
		for (File f : dir.listFiles()) {

			// Convert to txt
			String normalizedText = service.convertDocumentToText(f);

			// Now, we send this text to personality insight service
			System.out.println("Analyzing with personality insight...");

			Profile personalityProfile = pi.getProfile(normalizedText);


			// Build output
			String outName = f.getName();
			outName = outName.substring(0, outName.indexOf('.'))
					+ "_analysis.txt";

			// Print to file
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(OUTDIR + outName)));

			out.println(personalityProfile);
			out.close();
		}
	}
}
