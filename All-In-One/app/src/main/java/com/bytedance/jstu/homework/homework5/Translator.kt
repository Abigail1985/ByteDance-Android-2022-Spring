package com.bytedance.jstu.homework.homework5

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.jstu.homework.R
import okhttp3.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.bytedance.jstu.homework.homework5.api.TranslatorService
import com.bytedance.jstu.homework.homework5.api.TranslatorBean

class Translator : AppCompatActivity() {

    private var requestBtn: Button? = null
    private var showText: TextView? = null
    private var editword: EditText? = null

    private val okhttpListener = object : EventListener() {
        @SuppressLint("SetTextI18n")
        override fun dnsStart(call: Call, domainName: String) {
            super.dnsStart(call, domainName)
            updateShowTextView("\nDns Search: $domainName")
        }

        @SuppressLint("SetTextI18n")
        override fun responseBodyStart(call: Call) {
            super.responseBodyStart(call)
            updateShowTextView("\nResponse Start")
        }
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .eventListener(okhttpListener)
        .build()

    private var retrofit = Retrofit.Builder()
        .baseUrl("https://dict.youdao.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translator)
        editword = findViewById(R.id.edit_word)
        requestBtn = findViewById(R.id.send_request)
        showText = findViewById(R.id.show_text)

        requestBtn?.setOnClickListener {
            updateShowTextView("", false)
            click()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateShowTextView(text: String, append: Boolean = true) {
        if (Looper.getMainLooper() !== Looper.myLooper()) {
            // 子线程，提交到主线程中去更新 UI.
            runOnUiThread {
                updateShowTextView(text, append)
            }
        } else {
            showText?.text = if (append) showText?.text.toString() + text else text
        }
    }

    private fun request(callback: Callback<TranslatorBean>) {
        try {
            val translatorService = retrofit.create(TranslatorService::class.java)
            translatorService.getWordInfo("${editword?.text}").enqueue(callback)
        } catch (error: Throwable) {
            updateShowTextView("request err: ${error.message}", false)
            error.printStackTrace()
        }
    }

    private fun click() {
        request(object : Callback<TranslatorBean> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: retrofit2.Call<TranslatorBean>,
                response: Response<TranslatorBean>
            ) {
                val respFormatText = if (response.isSuccessful) {
                    val TranslatorBean = response.body()
//                    val values =""
//                    val keyvalue=""
//                    val keyvalues=""
//                    TranslatorBean?.web_trans?.webtranslation.forEach { it -> it.trans.forEach{ values+it.value+"  " } }
//                    TranslatorBean?.web_trans?.webtranslation.forEach { keyvalue+it.key+":"+values+"\n" }
//                    TranslatorBean?.web_trans?.webtranslation.forEach { keyvalues= }
                    "\n\n\nInput: ${TranslatorBean?.web_trans?.webtranslation?.get(0)?.key.toString()}  ${TranslatorBean?.web_trans?.webtranslation?.get(0)?.trans?.get(0)?.value}\nAlias: ${TranslatorBean?.lang}"
                } else {
                    "\n\n\nhttp status code: ${response.code()}, http status code: Response fail: ${response.errorBody()?.string()}."
                }
                updateShowTextView(respFormatText)
            }

            override fun onFailure(call: retrofit2.Call<TranslatorBean>, t: Throwable) {
                updateShowTextView(t.message.toString(), false)
            }
        })
    }
}