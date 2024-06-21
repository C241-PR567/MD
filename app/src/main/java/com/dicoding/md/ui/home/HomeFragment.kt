package com.dicoding.md.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.md.databinding.FragmentHomeBinding
import com.dicoding.md.ui.dashboard.DashboardViewModel
import com.dicoding.md.ui.detectionSkin.detectionSkinActivity
import com.dicoding.md.ui.skinDisease.SkinDiseaseActivity

class HomeFragment : Fragment() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]

        viewModel.dataUser.observe(viewLifecycleOwner){user ->
            binding.tvName.text = user.name
            binding.tvEmail.text = user.email
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner){ errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getDataUser(EXTRA_ID)

        binding.ibSkinDisease.setOnClickListener{
            val  intent = Intent(requireContext(), SkinDiseaseActivity::class.java)
            startActivity(intent)
        }

        binding.ibSkinDetection.setOnClickListener{
            val intent = Intent(requireContext(), detectionSkinActivity::class.java)
            startActivity(intent)
        }

        binding.tvName.setOnClickListener{
            val intent = Intent(requireContext(), detectionSkinActivity::class.java)
            startActivity(intent)
        }
    }
    companion object {
        internal const val EXTRA_ID = "id"
    }
}