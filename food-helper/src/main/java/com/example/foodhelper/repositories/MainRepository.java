package com.example.foodhelper.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Repository
public class MainRepository<T> {
    public String save(String collectionName, String id, T t) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = firestore.collection(collectionName).document(id).set(t);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public T get(String collectionName, String id, Class<T> valueType) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection(collectionName).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            return document.toObject(valueType);
        } else {
            return null;
        }
    }

    public List<T> getAll(String collectionName, Class<T> valueType) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        List<T> collection = new ArrayList<>();

        ApiFuture<QuerySnapshot> query = firestore.collection(collectionName).get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        for (QueryDocumentSnapshot document: documents) {
            collection.add(document.toObject(valueType));
        }

        return collection;
    }
}
