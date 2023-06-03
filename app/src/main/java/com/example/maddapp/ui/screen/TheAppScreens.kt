package com.example.maddapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.maddapp.R


sealed class Screen(val route:String,val icon: Int, @StringRes val resourceId: Int){
    object Home: Screen("Home", R.drawable.baseline_home_24, R.string.home_screen)
    object Courses: Screen("Courses", R.drawable.baseline_class_24,R.string.course_screen)
    object Grade: Screen("Grade", R.drawable.baseline_grade_24,R.string.grade_screen)
    object MyTech: Screen("MyTech", R.drawable.baseline_code_24,R.string.my_tech_screen)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(){
    val screens = listOf(
        Screen.Home,
        Screen.Courses,
        Screen.Grade,
        Screen.MyTech
    )

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar{
                screens.forEach{ screen ->
                    NavigationBarItem(
                        icon = {Icon(painterResource(screen.icon), contentDescription = null)},
                        label = {Text(stringResource(screen.resourceId))},
                        selected = currentDestination?.hierarchy?.any{it.route == screen.route} == true,
                        onClick = {
                            navController.navigate(screen.route){
                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = Screen.Home.route){
                HomeScreen()
            }
            composable(route= Screen.Courses.route){
//                CourseScreen()
                NavCourses()
            }
            composable(route = Screen.MyTech.route){
                MyTechScreen()
            }
            composable(route = Screen.Grade.route){
                GradeScreen()
            }
        }

    }


}

