package com.anxdre.lks2023.api

import android.util.Log
import android.widget.Toast
import java.net.HttpURLConnection
import java.net.URL

object ApiFactory {
    fun fetchData(url: String): String {
        val endpoint = URL(url)
        val urlConnection = endpoint.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"

        if (urlConnection.responseCode != 200) {
            throw RuntimeException("Failed : HTTP error code : " + urlConnection.responseCode)
        }

        try {
            return urlConnection.inputStream.bufferedReader().readText()
        } finally {
            urlConnection.disconnect()
        }
    }
}