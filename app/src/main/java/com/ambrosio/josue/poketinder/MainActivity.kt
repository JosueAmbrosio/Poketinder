package com.ambrosio.josue.poketinder

import PokemonAdapter
import PokemonResponse
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.ambrosio.josue.poketinder.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var listPokemon: List<PokemonResponse> = emptyList()

    private val adapter by lazy { PokemonAdapter(listPokemon) }

    private lateinit var binding : ActivityMainBinding

    private val viewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvTinderPokemon.adapter = adapter
        observeValues()
    }

    private fun observeValues(){
        viewModel.isLoading.observe(this){isLoading->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.pokemonList.observe(this){pokemonList->
            adapter.list = pokemonList
            adapter.notifyDataSetChanged()
        }

        viewModel.errorApi.observe(this){errorMessage ->
            showMessage(errorMessage)
        }
    }

    private fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
