package com.fofok.figuresapi.integrations.firestore;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("firestore")
public class FirestoreConfig {

    private String projectId;

}
