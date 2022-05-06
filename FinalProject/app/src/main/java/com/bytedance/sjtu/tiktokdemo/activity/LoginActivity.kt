package com.bytedance.sjtu.tiktokdemo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bytedance.sjtu.tiktokdemo.R


import android.content.Intent
import android.widget.*


class LoginActivity: AppCompatActivity() {


    private val account: EditText by lazy {
        findViewById(R.id.account)
    }

    private val password: EditText by lazy {
        findViewById(R.id.password)
    }

    private val remember: CheckBox by lazy {
        findViewById(R.id.remember)
    }

    private val login: Button by lazy {
        findViewById(R.id.login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val pref = getPreferences(MODE_PRIVATE)
        val isRemember = pref.getBoolean("remember_pw", false)
        if (isRemember) {
            account.setText(pref.getString("account", ""))
            password.setText(pref.getString("password", ""))
            remember.isChecked = true
        }

        login.setOnClickListener {
            if (account.text.isEmpty() || password.text.isEmpty()) {
                Toast.makeText(this, "account or password can not be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val editor = pref.edit()
            if ("admin" == account.text.toString() && "123456" == password.text.toString()) {
                if (remember.isChecked) {
                    editor.putBoolean("remember_pw", true)
                    editor.putString("account", account.text.toString())
                    editor.putString("password", password.text.toString())
                } else {
                    editor.putBoolean("remember_pw", false)
                }
                editor.apply()
                startActivity(Intent(this, InfoActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "account or password is not correct, please try again", Toast.LENGTH_SHORT).show()
            }
        }

    }
}