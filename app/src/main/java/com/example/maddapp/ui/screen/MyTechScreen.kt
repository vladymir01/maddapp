package com.example.maddapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyTechScreen(){
    val myTechViewModel:MyTechViewModel = viewModel()
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = RoundedCornerShape(topEnd = 40.dp, topStart = 40.dp),
        sheetContent = { TheSheetContent(myTechViewModel) }
    ) {
        TheScreenContent(scope,state,myTechViewModel)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheSheetContent(myTechViewModel: MyTechViewModel){
    Surface(
        modifier = Modifier.height(600.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
        ) {
            Text("Add a new Technology")
            Spacer(modifier = Modifier.height(24.dp))
            TextField(value = myTechViewModel.techName,modifier=Modifier.fillMaxWidth(), onValueChange = {myTechViewModel.getTechNameEntered(it)})
            Spacer(modifier = Modifier.height(24.dp))
            TextField(value = myTechViewModel.companyName,modifier=Modifier.fillMaxWidth(), onValueChange = {myTechViewModel.getCompanyNameEntered(it)})
            Spacer(modifier = Modifier.height(24.dp))
            TextField(value = myTechViewModel.yearCreated,modifier=Modifier.fillMaxWidth(), onValueChange = {myTechViewModel.getYearCreatedEntered(it)})
            Spacer(modifier = Modifier.height(24.dp))
            Button( modifier = Modifier.fillMaxWidth(),onClick = { myTechViewModel.doSubmit()}) {
                Text("Submit")
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TheScreenContent(scope: CoroutineScope, state: ModalBottomSheetState, myTechViewModel: MyTechViewModel){


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
                        Text(text = it.id)
                        Text(text = it.name)
                        Text(text = it.company)
                        Text(text = it.yearOfCreation)
                    }
                }

            }

        }
    }

}