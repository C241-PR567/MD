package com.dicoding.md.ui.skinDisease

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.md.data.response.DiseaseResponse
import com.dicoding.md.data.response.DiseasesItem
import com.dicoding.md.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiseaseViewModel : ViewModel() {
    private val _listDisease = MutableLiveData<List<DiseasesItem>>()
    val listDisease: LiveData<List<DiseasesItem>> = _listDisease

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val error: LiveData<String> = _errorMessage

    init {
        findSkinDisease()
    }

    private fun findSkinDisease() {
        _isLoading.value = true
        val user = ApiConfig.getApiService().getDiseaseList()
        user.enqueue(object : Callback<DiseaseResponse> {
            override fun onResponse(
                call: Call<DiseaseResponse>, response: Response<DiseaseResponse>
            ) {
                _isLoading.value = false
                val diseaseList = response.body()
                if (response.isSuccessful && diseaseList != null) {
                        _listDisease.value = (diseaseList.diseases)
                } else {
                    _errorMessage.value = "Gagal memuat data: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<DiseaseResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Terjadi kesalahan: ${t.message}"
            }
        })
    }

    fun searchUsers(query: String) {
        _isLoading.value = true
        ApiConfig.getApiService().searchDisease(query).enqueue(object : Callback<DiseaseResponse> {
            override fun onResponse(
                call: Call<DiseaseResponse>, response: Response<DiseaseResponse>
            ) {
                _isLoading.value = false
                val diseaseList = response.body()?.diseases
                if (response.isSuccessful && !diseaseList.isNullOrEmpty()) {
                    _listDisease.value = diseaseList!!
                } else {
                    _errorMessage.value = "Penyakit tidak ditemukan"
                }
            }

            override fun onFailure(call: Call<DiseaseResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Terjadi kesalahan: ${t.message}"
            }
        })
    }
}