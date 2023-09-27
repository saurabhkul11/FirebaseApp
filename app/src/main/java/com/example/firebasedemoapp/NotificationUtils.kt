package com.example.firebasedemoapp

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

@SuppressLint("UnspecifiedImmutableFlag")

var NOTIFICATION_ID=0

lateinit var pendingIntent:PendingIntent


fun NotificationManager.sendNotification(messageBody: String, context: Context) {

    val cId  = "fcm_default_channel"
    val dSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    val intent=Intent(context,MainActivity::class.java)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
         pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_MUTABLE)

    }
    else{
         pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_ONE_SHOT)

    }

    val appImage = BitmapFactory.decodeResource(context.resources, R.drawable.appicon)

    val builder = NotificationCompat.Builder(context, cId)
        .setSmallIcon(R.drawable.appicon)
        .setSound(dSoundUri)
        .setContentTitle(context.getString(R.string.notification_title))
        .setStyle(NotificationCompat.BigTextStyle().bigText(messageBody))
        .setContentText(messageBody)
        .setContentIntent(pendingIntent)
        .setLargeIcon(appImage)
        .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)

    builder.setSmallIcon(R.drawable.appicon)  //a resource for your custom small icon
        .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.appicon))

    val nManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val oChannel = NotificationChannel(cId, "Customer",NotificationManager.IMPORTANCE_HIGH)
        nManager.createNotificationChannel(oChannel)
    }

    notify(NOTIFICATION_ID, builder.build())

}