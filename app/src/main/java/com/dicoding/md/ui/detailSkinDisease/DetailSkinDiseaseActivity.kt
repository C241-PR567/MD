package com.dicoding.md.ui.detailSkinDisease

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.md.data.response.Data
import com.dicoding.md.databinding.ActivityDetailSkinDiseaseBinding

class DetailSkinDiseaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSkinDiseaseBinding
    private val DetailViewModel by viewModels<DetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSkinDiseaseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val id = intent.getStringExtra(EXTRA_ID) ?: return

        id.let {
            DetailViewModel.getDiseaseDetail(id)
        }

        DetailViewModel.displayDetailDisease.observe(this) { detailDisease ->
            displayDetailDisease(detailDisease)
        }

        DetailViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        DetailViewModel.error.observe(this) { isError ->
            if (isError != null) {
                Toast.makeText(this, isError, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun displayDetailDisease(detail: Data) {
        Glide.with(this)
            .load(detail.image)
            .into(binding.ivItemDisease)
        binding.tvItemName.text = detail.name
        binding.tvItemDescription.text = detail.description
        binding.tvCause.text = detail.penyebab
        binding.tvSolve.text = detail.penyebab
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    companion object {
        const val EXTRA_ID = "id"
    }
}