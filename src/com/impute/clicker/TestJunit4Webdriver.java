package com.impute.clicker;
import static org.junit.Assert.fail;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;




public class TestJunit4Webdriver {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private boolean flag=false;
	private StringBuffer verificationErrors = new StringBuffer();

	private ArrayList<String> al_searchQuery = new ArrayList<String>();
	private ArrayList<String> al_linkText= new ArrayList<String>();
	private String searchForText="^[\\s\\S]*Passion for Perfection[\\s\\S]*$";

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.google.co.in/";
		Dimension dd = new Dimension(1, 1);
		driver.manage().window().setSize(dd);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testJunit4Webdriver() throws Exception {

		try
		{
			// fetch the search query from the impute website
			URL searchQueryurl = new URL("http://www.impute.co.in/uploads/3/2/0/0/3200436/search_query.txt");
			BufferedReader searchQueryurlin = new BufferedReader(new InputStreamReader(searchQueryurl.openStream()));
			String searchQuerystr;
			while ((searchQuerystr = searchQueryurlin.readLine()) != null) {
				al_searchQuery.add(searchQuerystr);
			}
			searchQueryurlin.close();


			// fetch the linked text
			URL linkTexturl = new URL("http://www.impute.co.in/uploads/3/2/0/0/3200436/link_text.txt");
			BufferedReader linktextin = new BufferedReader(new InputStreamReader(linkTexturl.openStream()));
			String linkTextstr;
			while ((linkTextstr = linktextin.readLine()) != null) {
				al_linkText.add(linkTextstr);

			}
			linktextin.close();
			// search for text from impute website
			URL searchForurl = new URL("http://www.impute.co.in/uploads/3/2/0/0/3200436/search_for.txt");
			BufferedReader searchFortextin = new BufferedReader(new InputStreamReader(searchForurl.openStream()));
			String searchForTextstr;
			while ((searchForTextstr = searchFortextin.readLine()) != null) {
				searchForText.concat(searchForTextstr);

			}
			searchFortextin.close();

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		while(!(al_linkText.isEmpty()))
		{


			Dimension dd = new Dimension(1, 1);
			driver.manage().window().setSize(dd);
			flag=false;
			driver.get(baseUrl + "/");
			driver.findElement(By.id("gbqfq")).clear();
			driver.findElement(By.id("gbqfq")).sendKeys(al_searchQuery.get(0));
			driver.findElement(By.id("gbqfq")).sendKeys(Keys.RETURN);
			al_searchQuery.remove(0);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_SPACE);
			robot.keyPress(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_N);
			robot.keyRelease(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_ALT);


			while (!flag)
			{
				for (int second = 0;; second++) {
					if (second >= 60) fail("timeout");
					try { 
						if (driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Next[\\s\\S]*$")) 
						{
							// check if the link is present on this page
							if (driver.findElement(By.cssSelector("BODY")).getText().matches(searchForText))
								flag=true;
							break; 
						} 
					} catch (Exception e) {}
					Thread.sleep(1000);
				}
				// if link is present click it
				if(flag)
				{

					driver.findElement(By.linkText(al_linkText.get(0))).click();
					al_linkText.remove(0);
					System.out.println("Link clicked.");
					break;
				}
				else
					driver.findElement(By.xpath("//a[@id='pnnext']/span[2]")).click();

			}
			Thread.sleep(5000);


		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}