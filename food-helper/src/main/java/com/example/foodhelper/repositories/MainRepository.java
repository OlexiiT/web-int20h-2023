package com.example.foodhelper.repositories;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
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

    public Collection<T> getAll(String collectionName, Class<T> valueType) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();

        QuerySnapshot querySnapshot = firestore.collection(collectionName).get().get();
        Collection<T> collection = new ArrayList<>();

        for (QueryDocumentSnapshot queryDocumentSnapshot: querySnapshot) {
            System.out.println(queryDocumentSnapshot.getData());
            collection.add((T) (queryDocumentSnapshot.getData()));
        }

        return collection;
    }
}
