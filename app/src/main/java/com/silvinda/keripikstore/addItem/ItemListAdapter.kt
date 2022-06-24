package com.silvinda.keripikstore.addItem

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.silvinda.keripikstore.R
import com.silvinda.keripikstore.model.Keripik
import com.silvinda.keripikstore.update.UpdateItemActivity
import kotlinx.android.synthetic.main.list_product_item.view.*
import java.text.NumberFormat
import java.util.*

class ItemListAdapter(private val context: Context): RecyclerView.Adapter<ItemListAdapter.MyViewHolder>() {

    private var keripikList = emptyList<Keripik>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_product_item, parent, false))
    }

    override fun getItemCount(): Int {
        return keripikList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = keripikList[position]
        holder.itemView.tv_title.text = currentItem.judul
        holder.itemView.tv_kategori.text = currentItem.kategori
        currency(currentItem.price!!.toDouble(), holder.itemView.tv_price)
        holder.itemView.tv_rate.text = currentItem.rating

        Glide.with(context)
                .load(currentItem.gambar)
                .into(holder.itemView.iv_poster_image)

        holder.itemView.rowLayout.setOnClickListener {
            val intent = Intent(context, UpdateItemActivity::class.java).putExtra("keripik", currentItem)
            context.startActivity(intent)
        }
    }
    private fun currency(harga: Double, textView: TextView) {
        val localeId = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeId)
        textView.text = formatRupiah.format(harga)
    }
    fun setData(user: List<Keripik>){
        this.keripikList = user
        notifyDataSetChanged()
    }
}