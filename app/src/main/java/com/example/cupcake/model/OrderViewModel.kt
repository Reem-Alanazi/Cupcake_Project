package com.example.cupcake.model

import androidx.lifecycle.ViewModel
//need to import these
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel : ViewModel() {

    //"LiveData",so properties can be observable and UI can be updated when the source data in the view model changes.
    // Than I remove the initial values from the declaration of the properties in the class.
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<Double> = _price
    //?
    val dateOptions: List<String> = getPickupOptions()

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

    // heck if the flavor for the order has been set or not
    fun hasNoFlavor():Boolean{
       return _flavor.value.isNullOrBlank()
    }

    // create and return the list of pickup dates
    private fun getPickupOptions(): List<String>{
        val option = mutableListOf<String>()
        //"E MMM d", and the locale. In the pattern string, E stands for day name in week and it parses to "Tue Dec 10".
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        //this variable will contain the current date and time
        val calendar = Calendar.getInstance()
        // ?
        repeat(4){
           option.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE,1)
        }
        return option
    }

    // To Set initial values for the order
    init {
        resetOrder()
    }

    //reset the MutableLiveData properties in the view model
    fun resetOrder(){

     _quantity.value=0
     _flavor.value = ""
     _date.value = dateOptions[0]
     _price.value = 0.0
    }





}

