package com.example.practico4.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practico4.R
import com.example.practico4.databinding.ActivityPersonaListBinding
import com.example.practico4.models.Persona
import com.example.practico4.ui.adapters.PersonaListAdapter
import com.example.practico4.ui.viewmodels.PersonaListViewModel

class PersonaListActivity : AppCompatActivity(), PersonaListAdapter.PersonaItemListener {
    lateinit var binding: ActivityPersonaListBinding
    private val viewModel: PersonaListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPersonaListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModelObservers()
        setupRecyclerView()
        setupEventListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPersonaList()
    }

    private fun setupEventListeners() {
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, PersonaFormActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        val adapter = PersonaListAdapter(this)
        binding.rvPersonaList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@PersonaListActivity)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.personaList.observe(this) {
            val adapter = binding.rvPersonaList.adapter as PersonaListAdapter
            adapter.updateData(it)
        }

    }

    override fun onPersonaItemClick(persona: Persona) {
        val intent = PersonaDetailActivity.newIntent(this, persona.id)
        startActivity(intent)
    }
}