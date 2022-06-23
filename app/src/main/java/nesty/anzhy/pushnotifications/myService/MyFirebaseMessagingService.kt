package nesty.anzhy.pushnotifications.myService

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import nesty.anzhy.pushnotifications.R

class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        const val CHANNEL_ID = "2"
    }

    class MyFirebaseMessagingService() {}

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e("remoteMessage", remoteMessage.notification.toString())
        if (remoteMessage.notification != null) {
            // create and display notification
            showNotification(
                remoteMessage.notification!!.title.toString(),
                remoteMessage.notification!!.body.toString()
            )
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    private fun showNotification(title: String, text: String) {
        // Create notification channel
        createNotificationChannel()

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, CHANNEL_ID)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setSmallIcon(R.drawable.chihuahua)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }
        val notificationManagerCompat: NotificationManagerCompat =
            NotificationManagerCompat.from(this)
        notificationManagerCompat.notify(2, builder.build())
    }

    private fun createNotificationChannel() {
        // Create Notification Channel only on API Level 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel Name2"
            val description = "My Channel description2"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description

            //Register the channel with the system
            val manager: NotificationManager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}