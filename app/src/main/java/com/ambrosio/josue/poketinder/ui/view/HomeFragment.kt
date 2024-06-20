package com.ambrosio.josue.poketinder.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ambrosio.josue.poketinder.data.model.PokemonResponse
import com.ambrosio.josue.poketinder.databinding.FragmentHomeBinding
import com.ambrosio.josue.poketinder.ui.adapter.PokemonAdapter
import com.ambrosio.josue.poketinder.ui.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var listPokemon: List<PokemonResponse> = emptyList()

    private val adapter by lazy { PokemonAdapter(listPokemon) }

    private lateinit var binding: FragmentHomeBinding

    private val viewModel by lazy { HomeViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeValues()
    }

    private fun setupRecyclerView() {
        binding.rvTinderPokemon.adapter = adapter
    }

    private fun observeValues() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            binding.progressBar.isVisible = isLoading
        })

        viewModel.pokemonList.observe(viewLifecycleOwner, Observer { pokemonList ->
            adapter.list = pokemonList
            adapter.notifyDataSetChanged()
        })

        viewModel.errorApi.observe(viewLifecycleOwner, Observer { errorMessage ->
            showMessage(errorMessage)
        })
    }

    private fun showMessage(message: String) {
        activity?.runOnUiThread {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
    }
}
