package com.example.practico4.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.practico4.R
import com.example.practico4.databinding.ActivityPersonaDetailBinding
import com.example.practico4.ui.viewmodels.MainViewModel

class PersonaDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonaDetailBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonaDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadPostInfo()
        setupViewModelObservers()
        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.fabAddPhone.setOnClickListener {
            val personaId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
            val intent = PhoneFormActivity.newIntent(this, personaId)
            startActivity(intent)
        }
        binding.fabAddEmail.setOnClickListener {
            val personaId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
            val intent = EmailFormActivity.newIntent(this, personaId)
            startActivity(intent)
        }

        binding.fabDelete.setOnClickListener {
            val personaId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
            if (personaId != 0)
                viewModel.deletePersona(personaId)
            finish()
        }
        binding.fabEdit.setOnClickListener {
            val personaId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
            val intent = PersonaFormActivity.newEditIntent(this, personaId)
            startActivity(intent)
        }
    }

    private fun loadPostInfo() {
        val personaId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
        if (personaId == 0)
            return
        viewModel.getPostById(personaId)
    }


    private fun setupViewModelObservers() {
        viewModel.name.observe(this) {
            binding.lblName.text = it
        }
        viewModel.lastName.observe(this) {
            binding.lblLastName.text = it
        }
        viewModel.company.observe(this) {
            binding.lblCompany.text = it
        }
        viewModel.address.observe(this) {
            binding.lblAddress.text = it
        }
        viewModel.city.observe(this) {
            binding.lblCity.text = it
        }
        viewModel.state.observe(this) {
            binding.lblState.text = it
        }
        viewModel.profilePicture.observe(this) { imageUrl ->
            Glide.with(this)
                .load(imageUrl)
                .into(binding.imgProfile)
        }

        viewModel.personaDeleted.observe(this) { isDeleted ->
            if (isDeleted) {
                Toast.makeText(this, "Persona eliminada correctamente", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }

    companion object {
        const val PERSONA_ID_PARAM = "personaId"

        fun newIntent(context: Context, id: Int): Intent {
            return Intent(context, PersonaDetailActivity::class.java).apply {
                putExtra(PERSONA_ID_PARAM, id)
            }
        }
    }
}