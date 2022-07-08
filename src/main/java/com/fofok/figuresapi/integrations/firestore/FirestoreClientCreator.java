package com.fofok.figuresapi.integrations.firestore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Getter
@Configuration
public class FirestoreClientCreator {

    @Autowired
    private FirestoreConfig firestoreConfig;

    private Firestore database;

    @PostConstruct
    void init() {
        try {
            FileInputStream serviceAccount = new FileInputStream("/home/rebeca/Downloads/figures.json");

            FirebaseOptions.Builder options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId(firestoreConfig.getProjectId());

            FirebaseApp.initializeApp(options.build());

            this.database = FirestoreClient.getFirestore();
        } catch (Exception e) {
            System.out.println("Exception? Nunca nem vi!");
        }

    }
}

