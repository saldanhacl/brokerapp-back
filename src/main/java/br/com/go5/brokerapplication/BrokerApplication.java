package br.com.go5.brokerapplication;

import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
@EnableScheduling
public class BrokerApplication {

//    @Value("${go5.broker.firebase.database.url}")
//    private String databaseUrl;
//
//    @Value("${go5.broker.firebase.config.path}")
//    private String configPath;

    public static void main(String[] args) {
        SpringApplication.run(BrokerApplication.class, args);
    }

//    @Bean
//    public FirebaseAuth firebaseAuth() throws IOException {
//
//        InputStream serviceAccount = getClass().getResourceAsStream("/serviceAccountKey.json");
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://brokerapp-9427a.firebaseio.com").build();
//
//        FirebaseApp.initializeApp(options);
//
//        return FirebaseAuth.getInstance();
//    }

}
