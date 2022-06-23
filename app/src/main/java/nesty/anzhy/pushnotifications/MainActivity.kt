package nesty.anzhy.pushnotifications

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cookies = findViewById(R.id.cookies)
        buyBtn = findViewById(R.id.btnBuy)

        buyBtn.setOnClickListener {
            val numberOfCookies = cookies.text.toString()

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
                    ).setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }
            notificationManagerCompat = NotificationManagerCompat.from(this@MainActivity)
            // Notification ID is unique for each notification you create
            notificationManagerCompat.notify(1, builder.build())

        }
    }
}