package com.example.topbarapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var myTollBar: Toolbar
    lateinit var mainLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myTollBar = findViewById(R.id.toolbar)
        mainLayout = findViewById(R.id.main_layout)
        myTollBar.overflowIcon = AppCompatResources.getDrawable(this, R.drawable.more_vert)

        myTollBar.setNavigationOnClickListener {
            val textview: TextView = findViewById(R.id.textView)
            textview.text = "The navigation icon is clicked"
            Toast.makeText(this, "Navigation icon is clicked", Toast.LENGTH_SHORT).show()
        }

        myTollBar.setOnMenuItemClickListener {item ->
            when (item.itemId) {
                R.id.share -> onSendMessage()
                R.id.edit -> Snackbar.make(
                    mainLayout,
                    "This is edit",
                    Snackbar.LENGTH_INDEFINITE
                ). setAction("close", {})
                R.id.settings -> getAlertDialog()
            }

            true // to same co i "return true"
        }
    }

    private fun onSendMessage() {
        val msg = "Witam drugą grupę w kotlinie"
        val sendIntent = Intent(Intent.ACTION_SEND)
        sendIntent.type = "text/plain"
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg)
        startActivity(sendIntent)
    }

    private fun getAlertDialog() : AlertDialog.Builder {
        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("To są ustawienia")
            .setMessage(" I tak niczego nie możęsz ustawić w tej apce")
            .setPositiveButton("Yes", { dialog, which -> })
            .setPositiveButton("No", { dialog, which -> dialog.cancel() })
        return alert
    }
}