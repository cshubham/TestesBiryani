package com.dg.testesbiryani

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.MediaMetadata
import android.media.session.MediaController
import android.media.session.MediaSessionManager
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.util.Log
import androidx.core.app.NotificationCompat


class MyService: Service() {
//    val lyrics = "It starts with one, one thing, I don't know why\n" +
//            "It doesn't even matter how hard you try\n" +
//            "Keep that in mind, I designed this rhyme to explain in due time\n" +
//            "All I know, time is a valuable thing\n" +
//            "Watch it fly by as the pendulum swings\n" +
//            "Watch it count down to the end of the day, the clock ticks life away\n" +
//            "It's so unreal, didn't look out below\n" +
//            "Watch the time go right out the window\n" +
//            "Tryna hold on, d-didn't even know\n" +
//            "I wasted it all just to watch you go\n" +
//            "I kept everything inside and even though I tried, it all fell apart\n" +
//            "What it meant to me will eventually be a memory of a time when\n" +
//            "I tried so hard and got so far\n" +
//            "But in the end, it doesn't even matter\n" +
//            "I had to fall to lose it all\n" +
//            "But in the end, it doesn't even matter"

    val lyrics = "Hey Aditya\n" +
            "Maan ja bhai\n" +
            "le chal hame gadi\n" +
            "hawao ki ser karane\n" +
            "nhi toh jai\n" +
            "andi mandi shandi kr dega\n" +
            "chalo....\n"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("shch", "MyService onStartCommand " + " " + flags + " " + startId)


        var notification = getNotification(context = this, "Starting...")

        startForeground(1, notification)

//        val mediaSessionManager = getSystemService(MEDIA_SESSION_SERVICE) as MediaSessionManager
//        val controllers: List<MediaController> = mediaSessionManager.getActiveSessions(
//            ComponentName(
//                this,
//                NotificationListenerService::class.java
//            )
//        )
//
//        for (controller in controllers) {
//            val metadata: MediaMetadata? = controller.metadata
//            if (metadata != null) {
//                val title = metadata.getString(MediaMetadata.METADATA_KEY_TITLE)
//                val artist = metadata.getString(MediaMetadata.METADATA_KEY_ARTIST)
//                Log.d("NowPlaying", "Title: $title, Artist: $artist")
//            }
//        }

        val items = lyrics.split("\n")
        Thread {
            for (i in 0..items.size-1) {
                Log.d("shch", "service Task running: $i")
                Thread.sleep(3000) // Simulating work
                notification = getNotification(context = this, "" +  items[i])
                startForeground(1, notification)
            }
            stopSelf() // Stop service after work is done
        }.start()
        Log.d("shch", "MyService onStartCommand2 " + " " + flags + " " + startId)

        return Service.START_STICKY
    }

    override fun stopService(name: Intent?): Boolean {
        Log.d("shch", "MyService stopService")
        return super.stopService(name)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("shch", "MyService destroy")
    }

    companion object {
        fun getNotification(context: Context, text: String): Notification {
            val channelId = "foreground_service"
            val channelName = "Foreground Service"

            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
            context.getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

            val notification = NotificationCompat.Builder(context, channelId)
                .setContentTitle("In the End, it doesnt really matter")
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()
            return notification
        }
    }
}