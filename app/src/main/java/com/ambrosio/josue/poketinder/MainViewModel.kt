package com.ambrosio.josue.poketinder

import PokemonResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {

    val pokemonList = MutableLiveData<List<PokemonResponse>>()

    val isLoading = MutableLiveData<Boolean>()

    val errorApi = MutableLiveData<String>()

    init {
        getAllPokemon()
    }

    private fun getAllPokemon(){
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(PokemonApi::class.java).getPokemons()
            if (call.isSuccessful){
                call.body()?.let{
                    isLoading.postValue(false)
                    pokemonList.postValue(it.results)
                }
            }else{
                errorApi.postValue("Ha ocurrido un error")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}