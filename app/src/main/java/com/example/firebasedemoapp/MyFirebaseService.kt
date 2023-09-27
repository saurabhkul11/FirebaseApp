package com.example.firebasedemoapp

import android.app.NotificationManager
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseService:FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        message.notification.let {
            sendnotification(it?.body.toString())
        }

    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    fun sendnotification( str:String){
        val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java) as NotificationManager
        notificationManager.sendNotification(str, applicationContext)
    }
}