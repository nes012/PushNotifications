package nesty.anzhy.pushnotifications

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import nesty.anzhy.pushnotifications.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val numberOfCookies = intent.getStringExtra("cookie")

        val cookies = numberOfCookies!!.toInt()
        if (cookies < 50) {
            Toast.makeText(this, "You get small bonus.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "You get HUGE bonus!", Toast.LENGTH_SHORT).show()
        }
    }

}