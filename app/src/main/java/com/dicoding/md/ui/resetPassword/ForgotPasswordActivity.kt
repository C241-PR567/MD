package com.dicoding.md.ui.resetPassword

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.md.R
import com.dicoding.md.data.response.ResetPasswordRequest
import com.dicoding.md.data.response.ResetPasswordResponse
import com.dicoding.md.data.retrofit.ApiConfig
import com.dicoding.md.databinding.ActivityForgotPasswordBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnResetPassword.setOnClickListener { view ->
            val email = binding.edResetEmail.toString().trim()

            if (email.isNotEmpty()) {
                val resetPassword = ResetPasswordRequest(email)
                sendResetPassword(resetPassword)
                Toast.makeText(
                    this@ForgotPasswordActivity,
                    getString(R.string.email_empty),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val resetPassword = ResetPasswordRequest(email)

        }
    }

    private fun sendResetPassword(request: ResetPasswordRequest) {
        showLoading(true)
        val client = ApiConfig.getApiService().resetPassword(request)
        client.enqueue(object : Callback<ResetPasswordResponse> {
            override fun onResponse(
                call: Call<ResetPasswordResponse>,
                response: Response<ResetPasswordResponse>
            ) {
                showLoading(false)
                val resetPassword = response.body()
                if (response.isSuccessful && resetPassword != null) {
                    Toast.makeText(this@ForgotPasswordActivity, resetPassword.msg, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(
                        this@ForgotPasswordActivity, "Reset Password gagal: ${resetPassword?.msg}", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResetPasswordResponse>, t: Throwable) {
                    showLoading(false)
                    Toast.makeText(this@ForgotPasswordActivity, "Network Error: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

}