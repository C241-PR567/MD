package com.dicoding.md.ui.signUp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.md.R
import com.dicoding.md.data.response.SignUpRequest
import com.dicoding.md.databinding.ActivitySignUpBinding
import com.dicoding.md.ui.signIn.SignInActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel : SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerButton.setOnClickListener {
            val name = binding.edRegisterName.toString()
            val email = binding.edRegisterEmail.toString()
            val password = binding.edRegisterPassword.toString()
            val phone = binding.edRegisterPhone.toString()


            if (name.isBlank() || email.isBlank() || password.isBlank() || phone.isBlank()) {
                Toast.makeText(
                    this,
                    getString(R.string.warning_data_must_be_fill),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val signUpRequest = SignUpRequest(
                name = name,
                email = email,
                password = password,
                phone = phone
            )

            viewModel.signUp(signUpRequest)
        }

        viewModel.signUpResult.observe(this) { result ->
            if(result.success) {
                showToast(result.msg)
                val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                showToast(result.msg)
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        binding.tvHaveAccount.setOnClickListener {
            val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}