package com.silvinda.keripikstore.addItem

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener
import com.silvinda.keripikstore.databinding.ActivityAddItemBinding
import com.silvinda.keripikstore.model.Keripik
import com.silvinda.keripikstore.viewmodel.KeripikViewModel

class AddItemActivity : AppCompatActivity(), PermissionListener {
    private lateinit var binding: ActivityAddItemBinding
    private lateinit var filePath: Uri
    private lateinit var mKeripikViewModel: KeripikViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mKeripikViewModel = ViewModelProvider(this)[KeripikViewModel::class.java]

        binding.ivImg.setOnClickListener {
            ImagePicker.with(this).galleryOnly().start()
        }

        binding.btnTambah.setOnClickListener {
            insertData()
        }

        binding.btnLanjut3.setOnClickListener {
            finish()
        }
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {

        ImagePicker.with(this)
            .galleryOnly()	//User can only capture image using Camera
            .start()

    }

    override fun onPermissionRationaleShouldBeShown(
        permission: com.karumi.dexter.listener.PermissionRequest?,
        token: PermissionToken?
    ) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {
        //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(this, "Anda tidak bisa menambahkan foto produk", Toast.LENGTH_LONG ).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            Activity.RESULT_OK -> {
                //Image Uri will not be null for RESULT_OK
                filePath = data?.data!!
                Log.w("Oke", "filepath: $filePath")
                Glide.with(this)
                    .load(filePath)
                    .optionalCenterCrop()
                    .into(binding.ivImg)
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun insertData(){
        var kategori = binding.edtKategoriProduk.text.toString()
        var judul = binding.edtNamaProduk.text.toString()
        var gambar = filePath.toString()
        var rating = binding.edtRating.text.toString()
        var price = binding.edtHargaProduk.text.toString()
        var terlaris = binding.checkBox.isChecked
        var deskripsi = binding.edtRating3.text.toString()

        val keripik = Keripik(0, kategori, judul, rating, price, terlaris, gambar, deskripsi)
        mKeripikViewModel.addKeripik(keripik)
        Toast.makeText(this, "Produk berhasil ditambahkan", Toast.LENGTH_SHORT).show()
        finish()
    }
}