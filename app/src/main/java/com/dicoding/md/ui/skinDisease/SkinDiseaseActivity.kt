package com.dicoding.md.ui.skinDisease

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.md.data.response.DiseasesItem
import com.dicoding.md.databinding.ActivitySkinDiseaseBinding
import com.dicoding.md.ui.detailSkinDisease.DetailSkinDiseaseActivity

class SkinDiseaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySkinDiseaseBinding
    private lateinit var  adapter: SkinDiseaseAdapter
    private val mainViewModel by viewModels<DiseaseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySkinDiseaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.listDisease.observe(this) { listDisease ->
            setUserData(listDisease)
        }

        mainViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        mainViewModel.error.observe(this) { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvUser.layoutManager = LinearLayoutManager(this)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, _, _ ->
                val query = searchView.text.toString()
                searchUser(query)
                searchView.hide()
                true
            }
        }
    }
    private fun searchUser(query: String) {
        mainViewModel.searchUsers(query)
    }

    private fun setUserData(userItems: List<DiseasesItem>) {
        adapter = SkinDiseaseAdapter { disease ->
            val detailIntent = Intent(this, DetailSkinDiseaseActivity::class.java).apply {
                putExtra(DetailSkinDiseaseActivity.EXTRA_ID, disease.id)
            }
            startActivity(detailIntent)
        }
        adapter.submitList(userItems)
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}