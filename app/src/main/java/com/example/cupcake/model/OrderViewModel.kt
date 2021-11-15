package com.example.cupcake.model

import androidx.lifecycle.ViewModel
//need to import these
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private const val PRICE_PER_CUPCAKE = 2.00

class OrderViewModel : ViewModel() {

    //"LiveData",so properties can be observable and UI can be updated when the source data in the view model changes.
    // Than I remove the initial values from the declaration of the properties in the class.
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    //The formatted price will be a string with a currency symbol such as a ‘$'. You will fix the initialization error
    //se Transformations.map() to initialize the new variable,
    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }
    //?
    val dateOptions: List<String> = getPickupOptions()

    // write "_" before variable so make it just readable not editable out this class


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


    //update the price variable when the quantity is set.
    fun setQuantity(numberCupcakes:Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    //helper method to calculate the price
    private fun updatePrice(){

        _price.value = (quantity.value ?: 0) * PRICE_PER_CUPCAKE

        /*  The elvis operator (?:) means that if the expression on the left is not null,
          then use it. Otherwise if the expression on the left is null,
          then use the expression to the right of the elvis operator (which is 0 in this case). */
    }




}

