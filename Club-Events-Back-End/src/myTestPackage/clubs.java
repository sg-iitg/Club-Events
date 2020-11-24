package myTestPackage;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;


public class clubs{
	
	private static Firestore db;
	
	public void clubs_extraction(String link, String collection_name) throws InterruptedException, ExecutionException, IOException{
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe"); //enter chrome driver path
		WebDriver driver = new ChromeDriver(options);
		
		driver.get(link);
	
		WebElement email_box = driver.findElement(By.name("email"));
		WebElement password_box = driver.findElement(By.name("pass"));
		WebElement login_button = driver.findElement(By.id("u_0_3"));
		
		email_box.sendKeys();//enter fb username
		password_box.sendKeys();//fb password
		login_button.click();
		
		Thread.sleep(10000);
		
		WebElement post_button = driver.findElement(By.xpath("//*[text()='Posts']"));
		post_button.click();
		
		Thread.sleep(10000);
		
		Document doc = Jsoup.parse(driver.getPageSource());
		Elements posts = ((Element) doc).getElementsByClass("_1dwg _1w_m _q7o"); 
		

		 
		CollectionReference colRef = db.collection(collection_name);
		 
		Map<String, Object> data = new HashMap<>();
		 	
		for (Element post : posts) {
			String time_txt="";
			Elements stamps = post.getElementsByClass("_5ptz");
			
			for(Element time: stamps) {
				time_txt+=time.attr("data-utime");
				break;
			}

			String post_txt="";
			Elements post_content1 = post.getElementsByClass("_5pbx userContent _3576");
			Elements post_content2 = post.getElementsByClass("_5pbx userContent _3ds9 _3576");
			
			boolean finish = false;
			for(Element post_t: post_content1) {
				post_txt+=post_t.children().get(0).text().replace("See more"," ");
				finish=true;
				break;
			}
			
			if(!finish)
			{
				for(Element post_t: post_content2) {
					post_txt+=post_t.children().get(0).text().replace("See more"," ");
					finish=true;
					break;
				}
			}
			
			
			String link_txt="facebook.com";
			String temp="";
			Elements link_content = post.getElementsByClass("_5pcq");
			
			for(Element link_t: link_content) {
				temp+=link_t.attr("href");
				int k=0;
				while(temp.charAt(k)!='?')
				{
					link_txt+=temp.charAt(k);
					k++;
				}
				break;
			}
			
			String img_src="";
			Elements img1 = post.getElementsByClass("scaledImageFitWidth img");
			Elements img2 = post.getElementsByClass("scaledImageFitHeight img");
			
			boolean done=false;
			for(Element img_t: img1) {
				img_src += img_t.attr("src");
				done=true;
				break;
			}
			
			if(!done) {
				for(Element img_t: img2) {
					img_src += img_t.attr("src");
					done=true;
					break;
				}
			}
			
			 DocumentReference docRef = colRef.document(time_txt);
			 
			 TimeConverter obj = new TimeConverter();
		
			 data.clear(); 
			 data.put("EpochTime", time_txt);  
			 data.put("Content", post_txt); 
			 data.put("Time",obj.convertTime(Long.parseLong(time_txt))); 
			 data.put("Link", link_txt);
			 data.put("Image Src", img_src);
			 
			 ApiFuture<WriteResult> result = docRef.set(data); 
			 System.out.println("Update time : " + result.get().getUpdateTime());
		 		
		}
	}
	
	public static  void main(String[] args) throws IOException, InterruptedException, ExecutionException{
		
		FirebaseConnect firebase = new FirebaseConnect(); 
		firebase.init();
		 
		db = FirestoreClient.getFirestore();
		
		clubs obj = new clubs();
		
		obj.clubs_extraction("https://www.facebook.com/codingclubiitg/", "Coding Club");
		obj.clubs_extraction("https://www.facebook.com/electronics.iitg/", "Electronics Club");
		obj.clubs_extraction("https://www.facebook.com/quizclubiitg/", "Quiz Club");
		obj.clubs_extraction("https://www.facebook.com/ecell.iitg/", "E-Cell");
		obj.clubs_extraction("https://www.facebook.com/robotics.iitg/", "Robotics Club");
		obj.clubs_extraction("https://www.facebook.com/caciitg/", "Consulting And Analytics Club");
		obj.clubs_extraction("https://www.facebook.com/Aeroiitg/", "Aeromodelling Club");
		obj.clubs_extraction("https://www.facebook.com/financeclubiitg/", "Finance And Economics Club");
		obj.clubs_extraction("https://www.facebook.com/PrakritiClub/", "Prakriti Club");
		obj.clubs_extraction("https://www.facebook.com/equinox.iitg/", "Astronomy Club");
		obj.clubs_extraction("https://www.facebook.com/iitg.racing/", "Automobile Club");
		obj.clubs_extraction("https://www.facebook.com/4ilabiitg/", "4I Lab");
		obj.clubs_extraction("https://www.facebook.com/cadence.iitg/", "Cadence");
		obj.clubs_extraction("https://www.facebook.com/musicclubiitg/", "Octaves");
		obj.clubs_extraction("https://www.facebook.com/iitgmovieclub/", "Lumiere");
		obj.clubs_extraction("https://www.facebook.com/xpressionsiitg/", "Xpressions");
		obj.clubs_extraction("https://www.facebook.com/finearts.iitg/", "Fine Arts Club");
		obj.clubs_extraction("https://www.facebook.com/anchorenza.radiog/", "Anchorenza & RadioG");
		obj.clubs_extraction("https://www.facebook.com/montage.iitg/", "Montage");
		obj.clubs_extraction("https://www.facebook.com/litsociitg/", "Lit-Soc");
		obj.clubs_extraction("https://www.facebook.com/iitg.sports/", "Sports Club");
	}
}
	
	
