/*
 * Copyright 2019 Apple. All Rights Reserved.
 *
 * This software is the proprietary information of Apple.
 * Use is subject to license terms.
 */

package com.apple.client.scanner;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  This Thread acts as a Producer for producing the list of Images from the WebPages.
 *  
 * @author kkanaparthi
 */
public class ScannerThread implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(ScannerThread.class);

	private BlockingQueue<Map<String, Set<String>>> scannerQueue;
	private BlockingQueue<String> anchorQueue = new LinkedBlockingQueue<>();

	private String startPage = null;
	private String homePage = null;


	/**
	 * 
	 * @param consumerQueue
	 */
	public ScannerThread(BlockingQueue<Map<String, Set<String>>> scannerQueue,
			String startPage,String homePage) {
		this.scannerQueue = scannerQueue;
		this.startPage = startPage;
		this.homePage= homePage;
	}
	
	
	/**
	 * 
	 * @param consumerQueue
	 */
	public ScannerThread(BlockingQueue<String> anchorQueue) {
		this.anchorQueue = anchorQueue;
	}
	

	public ScannerThread() {
		
	}


	@Override
	public void run() {
		try {
			scanPages(startPage, logger);
		} catch (IOException e) {
			logger.error("The Exception in the Scanner Thread is : "
					+ e.getMessage());
		}
	}

	
	public static void main (String[] a) {
		try {
			new ScannerThread().scanPages("https://en.wikipedia.org/wiki/Main_Page", logger);
		} catch (IOException e) {
			logger.error(" The Exception while getting the anchor tags ",e);
		} 
	}
	/**
	 * This method scans each page and collects the Images from the WebPages.
	 * @throws IOException
	 * 
	 */
	public void scanPages(String scanPage, Logger log) throws IOException {
		log.info("Page that is going to Scanned is {} " , scanPage);
		try {
			Document doc = null;
			Elements imagesOnThisPage = null;

			if (scanPage != null && !scanPage.isEmpty()) {
				doc = Jsoup.connect(scanPage).ignoreContentType(true)
						.timeout(500 * 1000).get();
				imagesOnThisPage = doc.getElementsByTag("a");

				anchorQueue.add(imagesOnThisPage.attr("href"));
				
				logger.info(" imagesOnThisPage {} ",imagesOnThisPage);
				imagesOnThisPage.stream().forEach(element->logger.info(element.toString()));
				
			
			} else {
				if (log.isDebugEnabled()) {
					log.debug("Page that is Skipped is {}" , scanPage+" and is Null or Empty");
				}
			}
		} catch (Exception ex) {
			log.error("The Exception While Parsing the Pages is : {}" , ex);
		}
	}


	/**
	 * This method collects the imageLinks.
	 * @return
	 */
	public  Set<String> collectImageLinks(Elements images, Logger log) {
		// int countImages = 0;
		Set<String> imagesSet = new HashSet<>();
		if (images != null) {
			for (Element image : images) {
				try {
					String imageLink = image.toString();
					String imageSubStr = "";
					if (imageLink.contains("data-src")) {
						imageSubStr = imageLink.substring(imageLink
								.indexOf("data-src=\"") + 10);
					} else {
						imageSubStr = imageLink.substring(imageLink
								.indexOf("src=\"") + 5);
					}
					// log.info("subStr is " + imageSubStr);

					String imageStringEx = imageSubStr.substring(0,
							imageSubStr.indexOf("\""));
					// log.info("The hLinkSuffix  after update is "
					// + (imageStringEx));
					if (!imageStringEx.contains("googleads")
							&& !imageStringEx.contains("ads.bluelithium.com")
							&& !imageStringEx.contains("secure.adnxs.com")
							&& !imageStringEx.contains("a.tribalfusion.com")
							&& !imageStringEx.contains("sitemap.jsp")) {
						if (!imageStringEx.contains("http")
								&& !imageStringEx.contains(".com")) {
							imageStringEx = homePage + imageStringEx;
						}
					} else {
						imageStringEx = "";
					}
					if (log.isDebugEnabled()) {
						log.debug("The imageStringEx is After All is "
								+ imageStringEx);
					}
					if (imageStringEx != null && !imageStringEx.isEmpty()
							&& imageStringEx.trim().startsWith("http://")) {
						imagesSet.add(imageStringEx);
					}
				} catch (Exception ex) {
					log.error("Exception While collecting ImageLinks from img Elements of the Document "
							+ ex.getMessage());
				}
			}
		}
		return imagesSet;
	}
}

