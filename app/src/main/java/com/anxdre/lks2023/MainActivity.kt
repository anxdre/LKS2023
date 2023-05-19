package com.anxdre.lks2023

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.anxdre.lks2023.api.ApiFactory
import com.anxdre.lks2023.databinding.ActivityMainBinding
import com.anxdre.lks2023.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.json.JSONTokener


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).async {
            val result = ApiFactory.fetchData("https://dummyjson.com/products")
            val json = JSONObject(JSONTokener(result))

            if (json.getInt("total") != 0) {
                val jsonData = json.getJSONArray("products")
                val listOfProducts = arrayListOf<Product>()

                for (i in 0..20) {
                    val data = jsonData.getJSONObject(i)
                    
                    val product = Product(
                        data.getInt("id"),
                        data.getString("brand"),
                        data.getString("category"),
                        data.getString("description"),
                        data.getDouble("discountPercentage"),
                        listOf<String>(data.getJSONArray("images").getString(0)),
                        data.getInt("price"),
                        data.getDouble("rating"),
                        data.getInt("stock"),
                        data.getString("thumbnail"),
                        data.getString("title")
                    )
                    listOfProducts.add(product)
                }

                withContext(Dispatchers.Main) {
                    print(listOfProducts.size)
                    binding.rvMain.adapter = MainAdapter(listOfProducts)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CoroutineScope(Dispatchers.IO).cancel()
    }

}