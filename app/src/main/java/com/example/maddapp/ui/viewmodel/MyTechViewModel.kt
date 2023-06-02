package com.example.maddapp.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.maddapp.model.MyTech
import java.time.Instant

class MyTechViewModel:ViewModel() {


    var myTechList:List<String> = listOf("A","B","C")

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateUniqueId():String{
        val timestamp = Instant.now().toEpochMilli()
        return timestamp.toString()
    }
}