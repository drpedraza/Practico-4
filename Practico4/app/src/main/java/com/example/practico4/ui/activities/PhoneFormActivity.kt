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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practico4.R
import com.example.practico4.databinding.ActivityPhoneFormBinding
import com.example.practico4.models.Phone
import com.example.practico4.ui.adapters.PhoneListAdapter
import com.example.practico4.ui.viewmodels.PhoneInsertViewModel

class PhoneFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneFormBinding
    private val viewModel: PhoneInsertViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPhoneFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val personaId = intent.getIntExtra(PERSONA_ID_PARAM, 0)

        setupEventListeners(personaId)
        setupViewModelObservers()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPhoneList()
    }

    private fun setupRecyclerView() {
        val adapter = PhoneListAdapter()
        binding.rvPhoneList.apply {
            this.adapter = adapter
        }
    }

    private fun setupEventListeners(personaId: Int) {
        binding.btnSavePhone.setOnClickListener {
            val phone = Phone(
                id = 0,
                number = binding.lblPhoneItemNumber.text.toString(),
                persona_id = personaId,
                label = binding.lblPhoneItemLabel.text.toString()
            )
            viewModel.insertPhone(phone)
        }

        binding.btnCancelPhone.setOnClickListener {
            finish()
        }
    }

    private fun setupViewModelObservers() {
        viewModel.phoneInsert.observe(this) {
            Toast.makeText(this, "Telefono creada con éxito", Toast.LENGTH_SHORT).show()
            finish()
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, "Error al crear telefono: ${it.message}", Toast.LENGTH_SHORT).show()
        }
        viewModel.phoneList.observe(this) {
            val adapter = binding.rvPhoneList.adapter as PhoneListAdapter
            adapter.updateData(it)
        }
    }

    companion object {
        private const val PERSONA_ID_PARAM = "persona_id"

        fun newIntent(context: Context, id: Int): Intent {
            return Intent(context, PhoneFormActivity::class.java).apply {
                putExtra(PERSONA_ID_PARAM, id)
            }
        }
    }
}