package com.example.maddapp.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.maddapp.TAG

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleNavAppBar(
    modifier: Modifier = Modifier,
    currentScreen:String,
    canNavigateBack:Boolean,
    navigateUp: ()->Unit
){
    TopAppBar(
        title = { Text(currentScreen)},
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "The back arrow" )
                }
            }

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavCourses(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route?:"CoursesHome"

    Scaffold(
        topBar = {SimpleNavAppBar(
            currentScreen = currentScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = {navController.navigateUp()}
        )}

        ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "CoursesHome",
            modifier = Modifier.padding(innerPadding) ){

            composable(route = "CoursesHome" ){
                CourseListScreen(navController = navController)
            }

            composable(
                route = "Detail/{item}",
                arguments = listOf(navArgument("item") {
                    type = NavType.StringType
                    defaultValue = "Default"
                }
                ))
               {navBackStackEntry ->
                val itemId = navBackStackEntry.arguments?.getString("item")
                   itemId?.let { CourseDetailScreen(item = it) }
            }


            }

        }

    }




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseListScreen(navController: NavController,modifier: Modifier = Modifier){
    val levels = listOf(
        "Level1",
        "Level2",
        "Level3",
        "Level4"
    )
    
    Column {
        levels.forEach{level ->

                ListItem(
                    modifier=modifier.clickable {navController.navigate("Detail/$level") },
                    headlineText = { Text(level) },
                    leadingContent = {},
                    trailingContent = {
                        Icon(
                            Icons.Default.ArrowForward,
                            contentDescription = null
                        )
                    }
                )

            Divider()
        }
    }

}

@Composable
fun CourseDetailScreen (item:String ){
   Text(text = item)
}



