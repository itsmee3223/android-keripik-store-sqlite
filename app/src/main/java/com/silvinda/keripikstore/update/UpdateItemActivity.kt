package com.silvinda.keripikstore.update

import android.app.Activity
import android.app.AlertDialog
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
import com.silvinda.keripikstore.databinding.ActivityEditItemBinding
import com.silvinda.keripikstore.model.Keripik
import com.silvinda.keripikstore.viewmodel.KeripikViewModel

class UpdateItemActivity : AppCompatActivity(), PermissionListener {
    private lateinit var binding: ActivityEditItemBinding
    private var filePath: Uri? = null
    private lateinit var mKeripikViewModel: KeripikViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditItemBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mKeripikViewModel = ViewModelProvider(this)[KeripikViewModel::class.java]
        val data = intent.getParcelableExtra<Keripik>("keripik")

        binding.edtNamaProduk.setText(data?.judul)
        binding.edtKategoriProduk.setText(data?.kategori)
        binding.edtHargaProduk.setText(data?.price)
        binding.edtRating.setText(data?.rating)
        binding.edtRating3.setText(data?.deskripsi)
        binding.checkBox.isChecked = data?.terlaris == true

        Glide.with(this).load(data?.gambar).into(binding.ivImg)

        binding.ivImg.setOnClickListener {
            ImagePicker.with(this).galleryOnly().start()
        }

        binding.btnUpdate.setOnClickListener {
            updateItem(data)
        }

        binding.btnLanjut3.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setPositiveButton("Yes") { _, _ ->
                if (data != null) {
                    mKeripikViewModel.deleteKeripik(data)
                }
                Toast.makeText(this,
                    "Successfully removed: ${data?.judul}",
                    Toast.LENGTH_SHORT).show()
                finish()
            }
            builder.setNegativeButton("No") { _, _ -> }
            builder.setTitle("Delete ${data?.judul}?")
            builder.setMessage("Are you sure you want to delete ${data?.judul}?")
            builder.create().show()
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

    private fun updateItem(data: Keripik?){
        val gambar: String = if(filePath == null) {
            data?.gambar.toString()
        } else {
            filePath.toString()
        }
        val kategori = binding.edtKategoriProduk.text.toString()
        val judul = binding.edtNamaProduk.text.toString()
        val rating = binding.edtRating.text.toString()
        val price = binding.edtHargaProduk.text.toString()
        val terlaris = binding.checkBox.isChecked
        val deskripsi = binding.edtRating3.text.toString()

        val keripik = Keripik(data?.id!!.toInt(), kategori, judul, rating, price, terlaris, gambar, deskripsi)
        mKeripikViewModel.updateKeripik(keripik)
        Toast.makeText(this, "Produk berhasil diubah", Toast.LENGTH_SHORT).show()
        finish()
    }
}