package com.example.cube

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson

class DiceViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = getApplication<Application>().applicationContext

    private val _rolls = MutableLiveData<MutableList<Roll>>()
    val rolls: LiveData<MutableList<Roll>> = _rolls

    init {
        _rolls.value = loadRolls()
    }

    fun addRoll(roll: Roll) {
        val updatedList = _rolls.value ?: mutableListOf()
        updatedList.add(roll)
        _rolls.value = updatedList
        saveRolls(updatedList)
    }

    private fun saveRolls(rolls: List<Roll>) {
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val json = Gson().toJson(rolls)
        prefs.edit().putString("rolls", json).apply()
    }

    private fun loadRolls(): MutableList<Roll> {
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val json = prefs.getString("rolls", null)
        return if (json != null) {
            val type = object : com.google.gson.reflect.TypeToken<MutableList<Roll>>() {}.type
            Gson().fromJson(json, type)
        } else mutableListOf()
    }
}