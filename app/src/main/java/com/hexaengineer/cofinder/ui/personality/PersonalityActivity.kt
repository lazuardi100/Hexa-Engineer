package com.hexaengineer.cofinder.ui.personality

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hexaengineer.cofinder.databinding.ActivityPersonalityBinding
import com.hexaengineer.cofinder.ui.main.MainActivity

class PersonalityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonalityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Personality Test"

        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}