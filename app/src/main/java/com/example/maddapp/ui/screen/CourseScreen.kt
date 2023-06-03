package com.example.maddapp.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.maddapp.ui.viewmodel.CourseViewModel

//region App Bar
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
//endregion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavCourses(){
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




@Composable
fun CourseListScreen(navController: NavController){
    Column(modifier = Modifier.padding(top = 16.dp).verticalScroll(rememberScrollState())) {
        levels.forEach{level ->
            ExpandableLevel(navController = navController,title = level.level, myList = level.courses )
            Divider()
        }

    }
}

@Composable
fun CourseDetailScreen (item:String){
    val courseViewModel = CourseViewModel(item)
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Title: ${courseViewModel.courseInfo.title}")
        Divider()
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Course Code: ${courseViewModel.courseInfo.code}")
        Divider()
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Course Description")
        Text(text = courseViewModel.courseInfo.courseDescription)

    }



}

//region levelAndCourses
class Course( val code:String = "", val title: String = "",  val courseDescription:String="")
class Level( val level:String, val courses: List<Course>)

val level1 = Level(
    "Level1",
    listOf(
        Course("mad9013","Web Dev", "Technologies on the web evolve quickly. Every year brings new devices and with them new capabilities. These devices present many challenges and opportunities to web developers. Students review fundamentals of web development using hypertext markup language (HTML), and cascading style sheets (CSS), with a focus on developing responsive and mobile websites. Multiple IDEs are introduced and used to complete hands-on projects."),
        Course("mad9014", "Javascript", "While developing native applications for mobile devices holds many advantages, not every application requires direct access to native capabilities and there are many ways web developers can build applications using the programming skills they already possess. Students use HTML, CSS and Javascript to build applications which can quickly be deployed to multiple mobile platforms, such as iPhone and Android. Designing to conserve battery life is introduced as a concept in this course. Using mobile marketplaces to publish and market applications is introduced."),
        Course("mad9011", "Design", "Students are introduced to mobile design fundamentals and graphical user interface design tools. The design concepts include user interface visual elements, principles, accessibility and usability. Students gain production experience and skills implementing mobile industry-standard graphics and design tools, which help them create effective interfaces for mobile-first web and mobile applications. Students apply hands-on learning with different software packages to create visual elements, icons, splash graphics, scalable vector graphic (SVG) animations and wireframes for mobile user interfaces."),
        )
)


val level2 = Level(
    "Level2",
    listOf(
        Course("mad9022", "FrontEnd","Students expand on their web development skills from the first semester, while learning how to create a variety of web and mobile projects. Full Stack, JAMStack, Progressive Web Apps, and Hybrid Apps are explored as viable solutions for modern app projects. Maintenance requirements and the limitations of different platforms are also examined."),
        Course("mad9124", "BackEnd", "Students enhance their JavaScript skills to become productive with Full-stack development. They use a hands-on approach to build APIs using Node.JS and a variety of tools, frameworks, libraries and packages. The creation of these modern APIs also requires the students to develop skills with persistent scalable database storage systems. Project work culminates with students creating APIs to be used with websites and mobile applications. Students work individually as well as with other students to complete tasks."),
        Course("mad9020", "UI", "Students learn how to create functional, efficient and enjoyable interfaces. They gain an appreciation for the challenges of building websites and applications that remain functional across different devices and platforms. Usability that allows people to complete tasks on any device, any time, is emphasized. Focus is placed on practical application of students' design software experience in the creation of mobile application interfaces."),

        )
)

val level3 = Level(
    "Level3",
    listOf(
        Course("mad9135", "React", "Students leverage their full-stack JavaScript skills acquired in previous client-side and server-side development to build dynamic JavaScript Applications for both web and native (iOS and Android) platforms. They learn how to use frameworks, such as React and React Native, along with numerous task automation and productivity tools, while completing hands-on projects. Students also explore best practices for integrating automated testing tools into their development workflow."),
        Course("mad9137", "IOS", "Students use the Swift language within the standard Mac OSX development environment to create native applications for the iPhone and iPad. Working together in teams, students build and test applications. User interface requirements and building for a better user experience is stressed."),
        Course("mad9132", "Android", "Students learn to develop mobile applications for the Android mobile platform. Students use the Android application programming interface (API) and Android software development kit (SDK) for hands-on development of deployable mobile applications. Designing to conserve battery life on mobile devices is emphasized."),
        )
)

val level4 = Level(
    "Level4",
    listOf(
        Course("mad9145", "Applied Project", "Working in teams, students experience the analysis, design, implementation, testing and deployment of a mobile solution for a real-world client. Important topics from throughout their program of study are applied in this course. Faculty advisors facilitate student teams to demonstrate their skills in the applied project. Student teams make a technical presentation to their faculty advisors and participate in a public showcase of projects."),
        Course("mad9143", "Marketing", "The mobile landscape from a business point of view is discussed. Topics to be covered include business planning, revenue models, analytics, as well as other entrepreneurial skills. Students work together to explore the importance of networking within the context of mobile development projects."),
        Course("mad9146", "Maui", "Windows development can be accomplished through a variety of languages. Leveraging prior skills in creating mobile applications with object-oriented concepts, students focus on developing Windows mobile applications using XAML and C#. Designing to conserve battery life on mobile devices is stressed."),
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
//    descriptionFontWeight: FontWeight = FontWeight.Normal,
//    descriptionMaxLines: Int = 4,
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
                modifier = Modifier
                    .padding(padding)
                    .clickable { expandedState = !expandedState }
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
                            modifier = Modifier.clickable {navController.navigate("Detail/${item.code}")  }
                        )
                        Divider()
                    }

                }
            }

        }

    }
}


//endregion



