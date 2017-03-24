package assignmentpkg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;
/**
 * @author Shivani Jha
 *
 */
public class TCMAssignment1 {

	/**
	 * @param args
	 */
	
	static String driverPath = "C:\\TMAG\\TMAG Automation\\Selenium\\Projects\\CI_JUMP\\Storage\\";
	public static WebDriver driver;
	public static List<WebElement> myList;
	public static WebElement actor1 ;
	public static WebElement secondActor;	
	public static String[] movies = null;
	public static String[] expectedMovies = {"TO HAVE AND HAVE NOT(1944)", 
											"FALLEN ANGEL(1945)",
											"THOROUGHLY MODERN MILLIE(1967)",
											"ROME ADVENTURE(1962)",
											"THE SECRET GARDEN(1949)",""};
	public static String[] expectedOverview = 
		{"A skipper-for-hire's romance with a beautiful drifter is complicated by his growing involvement with the French resistance.",
			"A man is accused of killing a waitress he had tried to seduce with his wife''s money.",
			"A small-town girl hits the big city in search of romance Roaring Twenties style.",
			"A rebellious teacher moves to Rome and finds love.",
			"An orphaned girl changes the lives of those she encounters at a remote estate."		
			
	};
	public static String[] expectedActor1 = {"Walter Brennan","Dana Andrews","Tyler Moore","Angie Dickinson","Herbert Marshall"};
	public static String[] expectedSecondActor={"Hoagy Carmichael","Anne Revere","Beatrice Lillie","Constance Ford","Elsa Lanchester"};
	public static String[] expectedReleaseDate={"1944","1945","1967","1962","1949"};
	public static String movieName = null;
	
	
	
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
	// Test for IE Browser	
		System.setProperty("webdriver.ie.driver", driverPath + "IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		
// Load Webpage on IE 		
		
		driver.get("http://www.tcm.com/tcmdb/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Page title: " + driver.getTitle());
		
// Find Movie List 		
		myList = driver.findElements(By.xpath("//*[@id='movieSearchList']/div/a"));
		int linksCount = myList.size();
		movies = new String [linksCount];
		System.out.println("Total no of links Available: "+linksCount);
		//String links = myList.getText();
		
//For loop to verify first 5 Movies details		
		for(int i=0; i<5 ;i++)
		{
		movieName= myList.get(i).getAttribute("text");
		System.out.println(myList.get(i).getAttribute("text"));
		
		movies[i] = myList.get(i).getAttribute("href");
		
		
		driver.navigate().to(movies[i]);
		Thread.sleep(3000);
	
//Verify Movie Title 		
		WebElement movieHeader = driver.findElement(By.id("dbartimgtitle"));
	
		try {
			assertTrue(movieHeader.getText().contains(expectedMovies[i]));
			
			System.out.println ("Test Pass Movie Header :" + movieHeader.getText() + " contains :"+expectedMovies[i]);
			} 
		catch (Error e) {
				System.out.println ("error occured Title:" +e.toString());
			}

		
// Verify Movie Overview
		WebElement overView = driver.findElement(By.id("overViewbox"));
		try
		{
			assertTrue(overView.getText().contains(expectedOverview[i]));
			System.out.println ("Test Pass Movie Overview  :" + expectedOverview[i]);
		}
		catch (Error e) {
			System.out.println ("error occured in Overview :" +e.toString());
			System.out.println ("Test Expected Movie Overview  :" + expectedOverview[i]);
			System.out.println ("Test Fail Movie Overview  :" + overView.getText());
		}
//Verify Actors
// Third Movie page does not have top images so div positioning is different 	for Actors	. Usinf if statement to handle this for third movie. 
		if (i==2)
				{
					actor1 = driver.findElement(By.xpath("//*[@id='overViewbox']/div[7]/div[2]/div[2]/strong/a"));
					secondActor = driver.findElement(By.xpath("//*[@id='overViewbox']/div[7]/div[3]/div[2]/strong/a"));
				}
		else
			{
				actor1 = driver.findElement(By.xpath("//*[@id='overViewbox']/div[6]/div[2]/div[2]/strong/a"));
				secondActor = driver.findElement(By.xpath("//*[@id='overViewbox']/div[6]/div[3]/div[2]/strong/a"));
			}
		try
		{
		assertTrue(actor1.getText().contains(expectedActor1[i]));
		assertTrue(secondActor.getText().contains(expectedSecondActor[i]));
				System.out.println ("Test Pass Movie actors  :" + actor1.getText()  +" / " + secondActor.getText());
		}
		catch (Error e) {
			System.out.println ("error occured in actors :" +e.toString());
			System.out.println ("Expected Movie actors  :" + expectedActor1[i]  +" / " + (expectedSecondActor[i]));
			System.out.println ("Test Fail Movie actors  :" + actor1.getText()  +" / " + secondActor.getText());
	}
		
		
		WebElement releaseDate = driver.findElement(By.xpath("//*[@id='additional']/table/tbody/tr[2]/td[2]"));
		try
		{
		assertTrue(releaseDate.getText().contains(expectedReleaseDate[i]));
		System.out.println ("Test Pass Movie Release Date  :" + releaseDate.getText());
		}
		catch (Error e) {
			System.out.println ("error occured in Overview :" +e.toString());
			System.out.println ("Test Expected Movie Release Date  :" + expectedReleaseDate[i]);
			System.out.println ("Test Fail Movie Release Date  :" + releaseDate.getText());
		}
		System.out.println("Movie List : "+i +" "+ driver.getTitle());
		
		System.out.println("**************************************************************** "+i +" "+ driver.getTitle());
		driver.navigate().back();
		Thread.sleep(3000);
		myList = driver.findElements(By.xpath("//*[@id='movieSearchList']/div/a"));
		
		}   
				
		
			
		driver.close();
				
		

	}

}
