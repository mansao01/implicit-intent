package com.mansao.intentimplicit

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.mansao.intentimplicit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnWebsite.setOnClickListener(this@MainActivity)
            btnLocation.setOnClickListener(this@MainActivity)
            btnShare.setOnClickListener(this@MainActivity)
            btnCamera.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_website -> {
                val url = binding.edtWebsite.text.toString()
                val intentWeb = Intent(Intent.ACTION_VIEW)
                intentWeb.data = Uri.parse(url)
                startActivity(intentWeb)
            }

            R.id.btn_location -> {
                val location = binding.edtLocation.text.toString()
                val intentLocation = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$location"))
                intentLocation.setPackage("com.google.android.apps.maps")
                startActivity(intentLocation)
            }

            R.id.btn_share -> {
                val text = binding.edtShare.text.toString()

                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, text)
                    type = "text/plain"

                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

            R.id.btn_camera -> {

                val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (intentCamera.resolveActivity(packageManager) != null){
                    startActivity(intentCamera)
                }
            }
        }
    }
}