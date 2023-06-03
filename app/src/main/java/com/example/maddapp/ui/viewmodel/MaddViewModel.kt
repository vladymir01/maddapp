package com.example.maddapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.lifecycle.ViewModel
import com.example.maddapp.ui.MaddUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MaddViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(MaddUiState())
    val uiState: StateFlow<MaddUiState> = _uiState.asStateFlow()
    var gradeEntered by mutableStateOf("")
        private set

    fun getScoreEntered(valueEntered: String){
        gradeEntered = valueEntered
    }

    @OptIn(ExperimentalComposeUiApi::class)
    fun calculateFinalGrade(keyboard: SoftwareKeyboardController){

        val gradeINNumber = gradeEntered.toIntOrNull()?:0
        val finalMark = when(gradeINNumber){
            in 0..49 -> "F"
            in 50..52 -> "D-"
            in 53..56 -> "D"
            in 57..59 -> "D+"
            in 60..62 -> "C-"
            in 63..66 -> "C"
            in 67..69 -> "C+"
            in 70..72 -> "B-"
            in 73..76 -> "B"
            in 77..79 -> "B+"
            in 80..84 -> "A-"
            in 85..89 -> "A"
            in 90..100 -> "A+"

            else -> "NA"
        }

        _uiState.update { currentState ->
            currentState.copy(currentGrade = gradeINNumber,finalMark=finalMark)
        }

        gradeEntered = ""

        keyboard.hide()
    }
}