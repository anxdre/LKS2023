package com.anxdre.lks2023

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anxdre.lks2023.api.ApiFactory
import com.anxdre.lks2023.databinding.ActivityMainBinding
import com.anxdre.lks2023.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiTask = CoroutineScope(Dispatchers.IO).async {
            val result = ApiFactory.fetchData("https://dummyjson.com/products")
            Log.d("API_RESPONSE", result)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        CoroutineScope(Dispatchers.IO).cancel()
    }

}