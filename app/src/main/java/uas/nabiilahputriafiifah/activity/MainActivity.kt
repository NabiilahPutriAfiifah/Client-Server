package uas.nabiilahputriafiifah.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import uas.nabiilahputriafiifah.client_server.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val sharedPref = getSharedPreferences("USER_ACCOUNT", Context.MODE_PRIVATE)
            val is_login = sharedPref.getString("IS_LOGIN", null)
            if (sharedPref == null || is_login == null){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)
                finish()
            }
        },3000)
    }
}