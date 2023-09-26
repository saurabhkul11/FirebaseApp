package com.example.firebasedemoapp

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseService:FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        message.notification.hashCode()
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}