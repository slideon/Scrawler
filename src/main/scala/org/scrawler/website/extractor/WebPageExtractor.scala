package org.scrawler.website.extractor

import collection.JavaConverters._

import org.scrawler.website.WebPage

import org.openqa.selenium.htmlunit._
import org.openqa.selenium.WebElement

object WebPageExtractor {
   def getWebPage(driver: HtmlUnitDriver): WebPage = {
        val title = driver.getTitle
        val body = driver.findElementByXPath("//body").getText
        
        val headers1 = driver.findElementsByXPath("//h1").asScala.toSet.map({element: WebElement =>
            element.getText
        })
        
        val headers2 = driver.findElementsByXPath("//h2").asScala.toSet.map({element: WebElement =>
            element.getText
        })

        val keywords = (headers1 ++ headers2 ++ title.split(" ").toSet)
        val flattenedKeywords = keywords.map(_.split(" ").toSet).flatten

        new WebPage(title, body, flattenedKeywords)
   }
}