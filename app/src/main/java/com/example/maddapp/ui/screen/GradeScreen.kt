package com.example.maddapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.maddapp.R
import com.example.maddapp.ui.viewmodel.MaddViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GradeScreen(maddViewModel: MaddViewModel = viewModel()){
    val maddUiState by maddViewModel.uiState.collectAsState()
    val keyboard = LocalSoftwareKeyboardController.current

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        Surface(
//            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                Surface() {
                    Image(
                        painterResource(R.drawable.ac_school_of_media_and_design_logo),
                        contentDescription = stringResource(R.string.algonquin_logo)
                    )
                }
                Spacer(modifier = Modifier.width(32.dp))
                Text(
                    stringResource(R.string.grade_system),
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
        Surface() {
            Image(
                painterResource(R.drawable.madlogo),
                contentDescription = stringResource(R.string.madd_logo),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(192.dp)
            )
        }
        Spacer(modifier = Modifier.height(40.dp))
        MyTextField(
            gradeEntered = maddViewModel.gradeEntered,
            onUserTypeSore = {maddViewModel.getScoreEntered(it)},
            onKeyboardDone = { keyboard?.let { maddViewModel.calculateFinalGrade(it) } }
        )
        Spacer(modifier = Modifier.height(40.dp))
        mySubmitButton(onSubmitButton = { keyboard?.let { maddViewModel.calculateFinalGrade(it) } })
        Spacer(modifier = Modifier.height(40.dp))
        Surface(
//            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                stringResource(
                    R.string.grade_result,
                    maddUiState.currentGrade,
                    maddUiState.finalMark
                ),
//                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(16.dp),
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(
    gradeEntered:String,
    onUserTypeSore:(String)->Unit,
    onKeyboardDone:()-> Unit
){

    TextField(
        value = gradeEntered,
        onValueChange = onUserTypeSore,
        modifier=Modifier.fillMaxWidth(),
        singleLine = true,
        placeholder = { Text(text = "Enter your grade here")},
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {onKeyboardDone()}
        )
    )
}

@Composable
fun mySubmitButton(onSubmitButton:()->Unit){
    Button(
        onClick = onSubmitButton,
        Modifier.fillMaxWidth(),

    ) {
        Text(
            stringResource(R.string.submit_btn),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GradeScreenPreview(){
    GradeScreen()
}