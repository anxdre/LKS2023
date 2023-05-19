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
import org.json.JSONObject
import org.json.JSONTokener


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiTask = CoroutineScope(Dispatchers.IO).async {
            val result = ApiFactory.fetchData("https://dummyjson.com/products")
            val json = JSONObject(JSONTokener(result))

            if (json.getInt("total") != 0) {
                val listOfProducts = arrayListOf<Product>()
                val jsonData = json.getJSONArray("products")

                for (i in 0..20) {
                    val data = jsonData.getJSONObject(i)

                    val product = Product(
                        data.getInt("id"),
                        data.getString("brand"),
                        data.getString("category"),
                        data.getString("description"),
                        data.getDouble("discountPercentage"),
                        arrayListOf("test","test"),
                        data.getInt("price"),
                        data.getDouble("rating"),
                        data.getInt("stock"),
                        data.getString("thumbnail"),
                        data.getString("title")
                    )
                    listOfProducts.add(product)
                }
                Log.d("List of products", listOfProducts.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CoroutineScope(Dispatchers.IO).cancel()
    }

}