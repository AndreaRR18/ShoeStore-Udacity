package com.udacity.shoestore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private var isLogged: Boolean = false

    private val shoeList: MutableLiveData<List<Shoe>> by lazy {
         MutableLiveData<List<Shoe>>(
        listOf(
            Shoe("Name_1", 35.0, "company_1", "description_1"),
            Shoe("Name_2", 38.0, "company_2", "description_2"),
            Shoe("Name_3", 32.0, "company_3", "description_3"),
            Shoe("Name_4", 31.0, "company_4", "description_4"),
            Shoe("Name_5", 35.0, "company_5", "description_5")
        ))
    }

    fun getShoes(): LiveData<List<Shoe>> = shoeList

    fun isLogged(): Boolean = isLogged

    fun addNewElement(shoe: Shoe) {
        val newList: MutableList<Shoe> = shoeList.value.orEmpty().toMutableList()
        newList.add(shoe)
        shoeList.value = newList
    }

    fun setLogged() {
        isLogged = true
    }

}