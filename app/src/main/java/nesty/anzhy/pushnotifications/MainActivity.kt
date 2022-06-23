package nesty.anzhy.pushnotifications

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import nesty.anzhy.pushnotifications.utils.Constants.Companion.CHANNEL_ID

class MainActivity : AppCompatActivity() {

    private lateinit var cookies: EditText
    private lateinit var buyBtn: Button
    private lateinit var notificationManagerCompat: NotificationManagerCompat
    private lateinit var builder: NotificationCompat.Builder

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cookies = findViewById(R.id.cookies)
        buyBtn = findViewById(R.id.btnBuy)

        // Create notification channel if device is using API 26+
        createNotificationChannel()
        var intent = Intent(this@MainActivity, SecondActivity::class.java)

        buyBtn.setOnClickListener {
            val numberOfCookies = cookies.text.toString()

            var pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.getActivity(
                    this@MainActivity,
                    0,
                    intent,
                    PendingIntent.FLAG_MUTABLE
                )
            } else {
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
            }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder = NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle(
                    "NOTIFICATION USING " +
                            "KOTLIN"
                ).setContentText("You just bought $numberOfCookies Cookies!")
                    .setSmallIcon(R.drawable.chihuahua).setLargeIcon(
                        BitmapFactory.decodeResource(
                            this.resources, R.drawable
                                .chihuahua2
                        )
                    )
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }
            notificationManagerCompat = NotificationManagerCompat.from(this@MainActivity)
            // Notification ID is unique for each notification you create
            notificationManagerCompat.notify(1, builder.build())

        }
    }

    private fun createNotificationChannel() {
        // Create Notification Channel only on API Level 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "My Channel Name"
            val description = "My Channel description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description

            //Register the channel with the system
            val manager: NotificationManager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}