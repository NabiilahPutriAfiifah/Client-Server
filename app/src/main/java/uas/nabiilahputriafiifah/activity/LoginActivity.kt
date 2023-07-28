package uas.nabiilahputriafiifah.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import uas.nabiilahputriafiifah.DBHelper
import uas.nabiilahputriafiifah.client_server.R

class LoginActivity : AppCompatActivity() {
    private lateinit var signUp : Button
    private lateinit var login : Button
    private lateinit var unameLogin : EditText
    private lateinit var passLogin : EditText
    private lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signUp = findViewById(R.id.btnSignUp)
        unameLogin = findViewById(R.id.usernameLogin)
        passLogin = findViewById(R.id.passwordLogin)
        login = findViewById(R.id.btnLogin)
        dbHelper = DBHelper(this)

        signUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            val unameText = unameLogin.text.toString()
            val pwordText = passLogin.text.toString()

            if (TextUtils.isEmpty(unameText) || TextUtils.isEmpty(pwordText)){
                Toast.makeText(this, "Fill form correctly!", Toast.LENGTH_SHORT).show()
            }else{
                val auth = dbHelper.checkUserPass(unameText, pwordText)
                if (auth){
                    val sharedPref = getSharedPreferences("USER_ACCOUNT", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.apply{
                        putString("USERNAME", unameText)
                        putString("IS_LOGIN", "true")
                    }.apply()
                    val intent = Intent(this, HomePageActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Account not found!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}