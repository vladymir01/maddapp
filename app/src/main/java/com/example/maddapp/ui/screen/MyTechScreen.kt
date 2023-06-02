package com.example.maddapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.maddapp.ui.viewmodel.MyTechViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FloatingButton(scope: CoroutineScope, state: ModalBottomSheetState){
    FloatingActionButton(
        onClick = {scope.launch { state.show() }})
        {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyTechScreen(){
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp),
        sheetContent = { TheSheetContent() }
    ) {
        TheScreenContent(scope, state)
    }
}

@Composable
fun TheSheetContent(){
    Surface(
        modifier = Modifier.height(600.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Centered Text",
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TheScreenContent(scope: CoroutineScope, state: ModalBottomSheetState){
    val myTechViewModel:MyTechViewModel = viewModel()
    Scaffold(floatingActionButton = { FloatingButton(scope, state)})
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(Modifier.padding(innerPadding)) {
                myTechViewModel.myTechList.forEach {
                    Row {
                        Text(text = it)
                    }
                }

            }

        }
    }

}