package com.fourfifths.baedalsharing.domain.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class FCMNotification {
    private final String googleTokenPath;
    private final String databaseUrl;

    public FCMNotification(@Value("${firebase.private-key.path}") String googleTokenPath, @Value("${firebase.database-url}") String databaseUrl) throws IOException {
        this.googleTokenPath = googleTokenPath;
        this.databaseUrl = databaseUrl;
        ClassPathResource res = new ClassPathResource(googleTokenPath);
        InputStream inputStream = res.getInputStream();

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .setDatabaseUrl(databaseUrl) // https://<DATABASE_NAME>.firebaseio.com/
                .build();

        FirebaseApp.initializeApp(options);
    }

    public void sendMessageToOne(String registrationToken) throws FirebaseMessagingException {
        Message message = Message.builder()
                .putData("title", "fcm test on sendMessageToOne")
                .putData("score", "100")
                .setToken(registrationToken)
                .build();
        String response = FirebaseMessaging.getInstance().send(message);
    }

    public void sendMessage(List<String> registrationTokens) throws FirebaseMessagingException {
        MulticastMessage message = MulticastMessage.builder()
                .putData("title", "fcm test on sendMessageToOne")
                .putData("score", "100")
                .addAllTokens(registrationTokens)
                .build();
        BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);

        System.out.println(response.getSuccessCount() + " messages were sent successfully");
    }


}
