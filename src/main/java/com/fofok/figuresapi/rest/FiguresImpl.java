package com.fofok.figuresapi.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fofok.figuresapi.integrations.firestore.FirestoreClientCreator;
import com.fofok.figuresapi.interfaces.Figure;
import com.fofok.figuresapi.response.FigureBody;
import com.fofok.figuresapi.response.FigureType;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class FiguresImpl implements Figure {

    private static final Logger LOGGER = Logger.getLogger("FigureImpl");

    @Autowired
    private FirestoreClientCreator firestoreClient;

    private static final Gson GSON = new Gson();

    @Override
    public ResponseEntity<List<FigureBody>> getAllFigures(FigureType type) {
        String typeNormalized = type.getStringFigureType();
        List<FigureBody> response = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Firestore db = firestoreClient.getDatabase();
            ApiFuture<QuerySnapshot> query = db.collection("figure").get();

            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> mapDocument = new HashMap<>();
                String typeDocument = document.get("type").toString();
                if (typeDocument.equals(typeNormalized)) {
                    mapDocument.put("id", document.getId());
                    mapDocument.putAll(document.getData());
                    mapDocument.replace("type", FigureType.fromString(typeDocument));

                    response.add(objectMapper.convertValue(mapDocument, FigureBody.class));
                }
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("Exception? Nunca nem vi!");
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
    }

    @Override
    public ResponseEntity<String> getFigureById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateFigureById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<String> addFigureById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteFigureById(String id) {
        return null;
    }
}
