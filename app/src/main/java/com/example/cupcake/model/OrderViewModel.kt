package com.example.cupcake.model

import androidx.lifecycle.ViewModel
//need to import these
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class OrderViewModel : ViewModel() {

    //"LiveData",so properties can be observable and UI can be updated when the source data in the view model changes.
    private val _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>("")
    val flavor: LiveData<String> = _flavor

    private val _date = MutableLiveData<String>("")
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>(0.0)
    val price: LiveData<Double> = _price

    // write "_" before variable so make it just readable not editable out this class
    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
    }

}

