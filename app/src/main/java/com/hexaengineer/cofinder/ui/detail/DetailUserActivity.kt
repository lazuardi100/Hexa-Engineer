package com.hexaengineer.cofinder.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.hexaengineer.cofinder.R
import com.hexaengineer.cofinder.core.data.Resource
import com.hexaengineer.cofinder.core.domain.model.User
import com.hexaengineer.cofinder.core.ui.ViewModelFactory
import com.hexaengineer.cofinder.databinding.ActivityDetailUserBinding
import com.hexaengineer.cofinder.databinding.FragmentHomeBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val user = intent.getParcelableExtra<User>(EXTRA_DATA)
        val userId = user?.id
        viewModel.setId(userId.toString())
        viewModel.userDetail.observe(this, { userDetail ->
            if (userDetail != null) {
                when (userDetail) {
                    is Resource.Loading -> Log.d("Detail: ","Loading")
                    is Resource.Success -> {

                    }
                    is Resource.Error -> {

                    }
                }
            }
        })
    }

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
}