package com.example.maddapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
        sheetContent = { TheSheetContent(myTechViewModel,scope, state) }
    ) {
        TheScreenContent(scope,state,myTechViewModel)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun TheSheetContent(myTechViewModel: MyTechViewModel,scope: CoroutineScope, state: ModalBottomSheetState){
    val keyboard = LocalSoftwareKeyboardController.current
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
            TextField(
                value = myTechViewModel.techName,
                modifier=Modifier.fillMaxWidth(), 
                onValueChange = {myTechViewModel.getTechNameEntered(it)}, 
                placeholder = { Text(text = "Enter one of your favorite Tech")})
            
            Spacer(modifier = Modifier.height(24.dp))
            
            TextField(
                value = myTechViewModel.companyName,
                modifier=Modifier.fillMaxWidth(),
                onValueChange = {myTechViewModel.getCompanyNameEntered(it)},
                placeholder = { Text(text = "Enter the name of the Company")}
                )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            TextField(
                value = myTechViewModel.yearCreated,
                modifier=Modifier.fillMaxWidth(), 
                onValueChange = {myTechViewModel.getYearCreatedEntered(it)},
                placeholder = { Text(text = "Enter the year it was lauched")}
                )
            Spacer(modifier = Modifier.height(24.dp))
            Button( modifier = Modifier.fillMaxWidth(),onClick = { keyboard?.let{myTechViewModel.doSubmit(keyboard, scope, state)} }) {
                Text("Submit")
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TheScreenContent(scope: CoroutineScope, state: ModalBottomSheetState, myTechViewModel: MyTechViewModel){


    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "My Favorite Tech")}) },
        floatingActionButton = { FloatingButton(scope, state)})
    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
                myTechViewModel.myTechList.forEach {
                    ListItem(
                        headlineText = { Text(text = it.name)},
                        trailingContent = { Text(text = it.company)},
                        supportingText = { Text(text = "Launched in: ${it.yearOfCreation}")}
                        )
                    Divider()
            }

        }
    }

}