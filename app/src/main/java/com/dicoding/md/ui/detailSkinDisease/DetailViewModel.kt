package com.dicoding.md.ui.detailSkinDisease

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.md.data.response.Data
import com.dicoding.md.data.response.DetailDiseaseResponse
import com.dicoding.md.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _displayDetailDisease = MutableLiveData<Data>()
    val displayDetailDisease: LiveData<Data> = _displayDetailDisease

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getDiseaseDetail(id: String) {
        _isLoading.value = true
        val detail = ApiConfig.getApiService().getDetailDiseaseById(id)
        detail.enqueue(object : Callback<DetailDiseaseResponse> {
            override fun onResponse(
                call: Call<DetailDiseaseResponse>,
                response: Response<DetailDiseaseResponse>
            ) {
                _isLoading.value = false
                val detailDisease = response.body()
                if (response.isSuccessful && detailDisease != null) {
                    _displayDetailDisease.value = (detailDisease.data)
                } else {
                     _error.value = "onFailure: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<DetailDiseaseResponse>, t: Throwable) {
                _isLoading.value = false
                _error.value ="Terjadi kesalahan : ${t.message}"
            }
        })
    }

}