package com.dicoding.md.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.md.data.response.SignInResponse
import com.dicoding.md.data.response.UserData
import com.dicoding.md.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel(){
    private val _dataUser = MutableLiveData<UserData>()
    val  dataUser: LiveData<UserData> = _dataUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val error: LiveData<String> = _errorMessage

    fun getDataUser(uid: String) {
        _isLoading.value = true
        val call = ApiConfig.getApiService().getDataUser(uid)
        call.enqueue(object : Callback<SignInResponse> {
            override fun onResponse(
                call: Call<SignInResponse>,
                response: Response<SignInResponse>
            ) {
                _isLoading.value = false
                val dataUser = response.body()
                if (response.isSuccessful) {
                    _dataUser.value = (dataUser?.data)
                } else {
                    _errorMessage.value = "Terjadi kesalahan : ${response.message()}"
                }
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                _isLoading.value = false
                _errorMessage.value = "Terjadi kesalahan: ${t.message}"
            }
        })
    }
}