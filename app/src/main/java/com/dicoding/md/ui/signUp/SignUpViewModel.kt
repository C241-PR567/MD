package com.dicoding.md.ui.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.md.data.response.SignUpRequest
import com.dicoding.md.data.response.SignUpResponse
import com.dicoding.md.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel () : ViewModel() {
    private val _signUpResult = MutableLiveData<SignUpResponse>()
    val signUpResult: LiveData<SignUpResponse> = _signUpResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun signUp(signUpRequest: SignUpRequest) {

        _isLoading.value = true
        ApiConfig.getApiService().signUp(signUpRequest).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                _isLoading.value = false
                val signUpResponse = response.body()
                if (response.isSuccessful && signUpResponse != null) {
                    _signUpResult.value = SignUpResponse(success = true, msg = toString())
                } else {
                    _signUpResult.value = SignUpResponse(success = false, msg = toString())
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                _isLoading.value = false

            }
        })
    }
}