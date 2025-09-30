package com.example.cube

import android.content.Context
import androidx.fragment.app.activityViewModels
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cube.databinding.FragmentDiceBinding
import kotlin.random.Random


class DiceFragment: Fragment(R.layout.fragment_dice) {
    private var _binding: FragmentDiceBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DiceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.rolls.observe(viewLifecycleOwner) { rolls ->
            if (rolls.isNotEmpty()) {
                binding.textView.text = rolls.last().text
            }
        }
        binding.btnDice.setOnClickListener {
            val result = Random.nextInt(6) + 1
            binding.textView.text = result.toString()
            viewModel.addRoll(Roll(result.toString()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
