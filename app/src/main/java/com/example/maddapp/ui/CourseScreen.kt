package com.example.maddapp.ui

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
        title = {
                    if(currentScreen == "CoursesHome"){
                        Text("Courses")
                    }else{Text("Course Detail")}
                },
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
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        levels.forEach{level ->
            ExpandableLevel(navController = navController,title = level.level, myList = level.courses )
            Divider()
        }

    }
}

@Composable
fun CourseDetailScreen (item:String ){
   Text(text = item)
}

//region levelAndCourses
class Course( val title: String = "",  val courseDescription:String="")
class Level( val level:String, val courses: List<Course>)

val level1 = Level(
    "Level1",
    listOf<Course>(
        Course("mad9013", "Web Dev"),
        Course("mad9014", "Javascript"),
        Course("mad9011", "Design"),
    )
)


val level2 = Level(
    "Level2",
    listOf<Course>(
        Course("mad9022", "FrontEnd"),
        Course("mad9124", "BackEnd"),
        Course("mad9020", "UI"),
    )
)

val level3 = Level(
    "Level3",
    listOf<Course>(
        Course("mad9135", "React"),
        Course("mad9137", "IOS"),
        Course("mad9136", "Android"),
    )
)

val level4 = Level(
    "Level4",
    listOf<Course>(
        Course("mad9145", "Applied Project"),
        Course("mad9143", "Marketing"),
        Course("mad9146", "Maui"),
    )
)

val levels:List<Level> = listOf(level1, level2, level3, level4)

//endregion

//region expandableFunction
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableLevel(
    navController:NavController,
    title: String,
    titleFontWeight: FontWeight = FontWeight.Bold,
    myList: List<Course>,
    descriptionFontWeight: FontWeight = FontWeight.Normal,
    descriptionMaxLines: Int = 4,
    padding: Dp = 12.dp
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        color = MaterialTheme.colorScheme.secondary,

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(padding).clickable { expandedState = !expandedState }
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = title,
                    fontWeight = titleFontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expandedState) {
                Column{
                    myList.forEach{item ->
                        ListItem(
                            headlineText = { Text(item.title) },
                            trailingContent = {
                        Icon(
                            Icons.Default.ArrowForward,
                            contentDescription = null
                            ) },
                            modifier = Modifier.clickable {navController.navigate("Detail/${item.title}")  }
                        )
                        Divider()
                    }

                }
            }

        }

    }
}


//endregion



