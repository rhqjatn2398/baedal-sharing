package com.fourfifths.baedalsharing.domain.fcm;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FCMNotificationTest {
    FCMNotification fcmNotification;

    public FCMNotificationTest(FCMNotification fcmNotification) {
        this.fcmNotification = fcmNotification;
    }

    @Test
    void sendMessageToOne() throws FirebaseMessagingException {
        String registrationToken = "";
        fcmNotification.sendMessageToOne(registrationToken);
    }

    @Test
    void sendMessage() throws FirebaseMessagingException {
        List<String> registrationTokens = Arrays.asList(
                "",
                "",
                ""
        );
        fcmNotification.sendMessage(registrationTokens);
    }
}