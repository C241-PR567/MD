package com.dicoding.md.ui.signIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.md.data.response.SignInRequest
import com.dicoding.md.data.response.SignInResponse
import com.dicoding.md.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {

    private val _signInResult = MutableLiveData<SignInResponse>()
    val signInResult: LiveData<SignInResponse> = _signInResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun signIn(signInRequest: SignInRequest) {

        _isLoading.value = true
        ApiConfig.getApiService().signIn(signInRequest).enqueue(object : Callback<SignInResponse> {
            override fun onResponse(call: Call<SignInResponse>, response: Response<SignInResponse>) {
                _isLoading.value = false
                val signUpResponse = response.body()
                if (response.isSuccessful && signUpResponse != null) {

                } else {

                }
            }

            override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                _isLoading.value = false

            }
        })
    }
}