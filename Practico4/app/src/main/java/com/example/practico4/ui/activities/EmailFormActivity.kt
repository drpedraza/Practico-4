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
import com.example.practico4.databinding.ActivityEmailFormBinding
import com.example.practico4.models.Email
import com.example.practico4.ui.viewmodels.EmailInsertViewModel

class EmailFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailFormBinding
    private val viewModel: EmailInsertViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEmailFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val personaId = intent.getIntExtra(PERSONA_ID_PARAM, 0)
        setupEventListeners(personaId)
        setupViewModelObservers()
    }

    private fun setupEventListeners(personaId: Int) {
        binding.btnSaveEmail.setOnClickListener {
            val email = Email(
                id = 0,
                email = binding.lblEmailItemEmail.text.toString(),
                persona_id = personaId,
                label = binding.lblEmailItemLabel.text.toString()
            )
            viewModel.insertEmail(email)
        }

        binding.btnCancelEmail.setOnClickListener {
            finish()
        }

    }

    private fun setupViewModelObservers() {
        viewModel.emailInsert.observe(this) {
            Toast.makeText(this, "Correo creado con éxito", Toast.LENGTH_SHORT).show()
            finish()
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, "Error al crear telefono: ${it.message}", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val PERSONA_ID_PARAM = "persona_id"

        fun newIntent(context: Context, id: Int): Intent {
            return Intent(context, EmailFormActivity::class.java).apply {
                putExtra(PERSONA_ID_PARAM, id)
            }
        }
    }
}