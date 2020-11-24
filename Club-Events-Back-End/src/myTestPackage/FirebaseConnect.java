package myTestPackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseConnect {

	public void init() throws IOException {
		//provide path to your firebase Admin SDK credentials file
		InputStream serviceAccount = new FileInputStream();
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .build();
		FirebaseApp.initializeApp(options);
	}
}
