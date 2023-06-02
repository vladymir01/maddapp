package com.example.maddapp.ui.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.maddapp.TAG
import com.example.maddapp.model.MyTech
import java.time.Instant

class MyTechViewModel:ViewModel(){

    val tech1 = MyTech("231", "Tech1","Company A", "1998")
    val tech2 = MyTech("232", "Tech2", "Company B", "1990")
    val tech3 = MyTech("233", "Tech3", "Company C", "2013")
    var myTechList = mutableStateListOf(tech1, tech2, tech3)

    var techName by mutableStateOf("")
        private set
    var companyName by mutableStateOf("")
        private set
    var yearCreated by mutableStateOf("")
        private set

    fun getTechNameEntered(valueEntered: String){
        techName = valueEntered
    }
    fun getCompanyNameEntered(valueEntered: String){
        companyName = valueEntered
    }
    fun getYearCreatedEntered(valueEntered: String){
        yearCreated = valueEntered
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun doSubmit(){
        var newTech = MyTech(generateUniqueId(), techName, companyName, yearCreated)
        createTech(newTech)
//        Log.d(TAG,"$myTechList")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createTech(tech:MyTech){
        myTechList.add(MyTech(generateUniqueId(), tech.name, tech.company, tech.yearOfCreation))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateUniqueId():String{
        val timestamp = Instant.now().toEpochMilli()
        return timestamp.toString()
    }
}