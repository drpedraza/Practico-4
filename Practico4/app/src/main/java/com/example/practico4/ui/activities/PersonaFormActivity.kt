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
import com.example.practico4.R
import com.example.practico4.databinding.ActivityPersonaFormBinding
import com.example.practico4.models.Persona
import com.example.practico4.ui.viewmodels.PersonaInsertViewModel

class PersonaFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonaFormBinding
    private val viewModel: PersonaInsertViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonaFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.getBooleanExtra(PERSONA_EDIT_MODE, false)) {
            val personaId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
            if (personaId != 0) {
                viewModel.loadPersona(personaId)
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()
    }

    private fun setupEventListeners() {
        binding.btnSave.setOnClickListener {
            val persona = Persona(
                id = intent.getIntExtra(PERSONA_ID_PARAM, 0),
                name = binding.txtName.text.toString(),
                last_name = binding.txtLastName.text.toString(),
                company = binding.txtCompany.text.toString(),
                address = binding.txtAddress.text.toString(),
                city = binding.txtCity.text.toString(),
                state = binding.txtState.text.toString()
            )

            if (intent.getBooleanExtra(PERSONA_EDIT_MODE, false)) {
                viewModel.updatePersona(persona.id, persona)
            } else {
                viewModel.insertPersona(persona)
            }
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun setupViewModelObservers() {
        viewModel.persona.observe(this) { persona ->
            persona?.let {
                binding.txtName.setText(it.name)
                binding.txtLastName.setText(it.last_name)
                binding.txtCompany.setText(it.company)
                binding.txtAddress.setText(it.address)
                binding.txtCity.setText(it.city)
                binding.txtState.setText(it.state)
            }
        }

        viewModel.personaInsert.observe(this) {
            Toast.makeText(this, "Persona creada con éxito", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, PersonaListActivity::class.java))
            finish()
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, "Error al crear persona: ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val PERSONA_ID_PARAM = "personaId"
        private const val PERSONA_EDIT_MODE = "editMode"

        fun newEditIntent(context: Context, id: Int): Intent {
            return Intent(context, PersonaFormActivity::class.java).apply {
                putExtra(PERSONA_ID_PARAM, id)
                putExtra(PERSONA_EDIT_MODE, true)
            }
        }
    }
}
