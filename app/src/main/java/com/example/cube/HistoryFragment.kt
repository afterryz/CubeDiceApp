package com.example.cube

import android.content.Context
import androidx.fragment.app.activityViewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cube.databinding.FragmentHistoryBinding

class HistoryFragment: Fragment(R.layout.fragment_history) {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DiceViewModel by activityViewModels()

    private fun loadRolls(): MutableList<Roll> {
        val prefs = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val json = prefs.getString("rolls", null)
        return if (json != null) {
            val type = object : com.google.gson.reflect.TypeToken<MutableList<Roll>>() {}.type
            com.google.gson.Gson().fromJson(json, type)
        } else mutableListOf()
    }
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = HistoryAdapter(loadRolls())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.rolls.observe(viewLifecycleOwner) { rolls ->
            adapter.updateRolls(rolls)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}