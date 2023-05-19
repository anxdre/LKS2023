package com.anxdre.lks2023

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anxdre.lks2023.model.Product

class MainAdapter(private val dataSet: ArrayList<Product>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false),
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.tvName.text = "Nama Produk : ${dataSet[position].title}"
        holder.tvPrice.text = "Harga Produk : ${dataSet[position].price}"
        holder.tvBrand.text = "Brand Produk : ${dataSet[position].brand}"
    }

    override fun getItemCount(): Int = dataSet.size

    class MainViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        val tvPrice = itemView.findViewById<TextView>(R.id.tv_price)
        val tvBrand = itemView.findViewById<TextView>(R.id.tv_brand)
    }
}

