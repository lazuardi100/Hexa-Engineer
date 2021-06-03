package com.hexaengineer.cofinder.ui.personality

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.hexaengineer.cofinder.core.ui.ViewModelFactory
import com.hexaengineer.cofinder.databinding.ActivityPersonalityBinding
import com.hexaengineer.cofinder.ui.main.MainActivity

class PersonalityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalityBinding
    private lateinit var viewModel: PersonalityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Personality Test"

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[PersonalityViewModel::class.java]

        binding.button.setOnClickListener {
            val words = binding.edtPersonality.text
            viewModel.postPersonality("1", words.toString())
            Toast.makeText(this, "Sukses", Toast.LENGTH_SHORT).show()
            Log.d("Personality: ", words.toString())
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}