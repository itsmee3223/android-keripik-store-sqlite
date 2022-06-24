package com.silvinda.keripikstore.detail

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.silvinda.keripikstore.checkout.CheckoutSuccessActivity
import com.silvinda.keripikstore.databinding.ActivityDetailBinding
import com.silvinda.keripikstore.model.Keripik
import java.text.NumberFormat
import java.util.*

class DetailActivity: AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val data = intent.getParcelableExtra<Keripik>("keripik")

        binding.tvTitle.text = data?.judul
        binding.tvGenre.text = data?.kategori
        binding.tvDesc.text = data?.deskripsi
        binding.tvRate.text = data?.rating
        if(!data?.equals("")!!) {
            currency(data.price.toDouble(), binding.tvPrice)
        } else {
            binding.tvPrice.text = "Rp0"
        }

        Glide.with(this)
            .load(data.gambar)
            .into(binding.ivPoster)

        binding.btnBeli.setOnClickListener {
            startActivity(Intent(this@DetailActivity, CheckoutSuccessActivity::class.java))
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun currency(harga: Double, textView: TextView) {
        val localeId = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeId)
        textView.text = formatRupiah.format(harga)
    }
}