package com.example.maddapp.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.lifecycle.ViewModel
import com.example.maddapp.model.MyTech
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.Instant

class MyTechViewModel:ViewModel(){

    private val tech1 = MyTech("231", "JavaScript","Company A", "1998")
    private val tech2 = MyTech("232", "Tech2", "Company B", "1990")
    private val tech3 = MyTech("233", "Tech3", "Company C", "2013")
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


    @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    fun doSubmit(keyboard: SoftwareKeyboardController, scope: CoroutineScope, state: ModalBottomSheetState){
        val newTech = MyTech(generateUniqueId(), techName, companyName, yearCreated)
        createTech(newTech)
        keyboard.hide()
        scope.launch { state.hide() }
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