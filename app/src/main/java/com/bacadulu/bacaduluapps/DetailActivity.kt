package com.bacadulu.bacaduluapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bacadulu.bacaduluapps.databinding.ActivityDetailBinding
import com.bacadulu.bacaduluapps.model.Books

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val books: Books? = intent.getParcelableExtra<Books>("DETAIL_SPORTS_DATA")

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mypdf.fromAsset(R.string.title_book1.toString()).load()
    }
}