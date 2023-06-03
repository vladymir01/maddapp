package com.example.maddapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.maddapp.R

@Composable
fun HomeScreen(){

                Column {
                    Image(
                        painterResource(id = R.drawable.madapp),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .offset(y=(-16).dp)
                    )
                    Column(Modifier.padding(16.dp)) {
                        Text(text = "Mobile Application Design And Development")
                        Divider(Modifier.padding(bottom = 16.dp))
                        Row {
                            Icon(painterResource(id = R.drawable.baseline_arrow_right_24), contentDescription = null )
                            Text(text = "user interface (UI) design", Modifier.padding(bottom = 8.dp, start = 8.dp))
                        }

                        Row {
                            Icon(painterResource(id = R.drawable.baseline_arrow_right_24), contentDescription = null )
                            Text(
                                text = "user experience (UX) design",
                                Modifier.padding(bottom = 8.dp, start = 8.dp)
                            )
                        }

                        Row {
                            Icon(painterResource(id = R.drawable.baseline_arrow_right_24), contentDescription = null )
                            Text(
                                text = "responsive web design",
                                Modifier.padding(bottom = 8.dp, start = 8.dp)
                            )
                        }
                        Row {
                            Icon(painterResource(id = R.drawable.baseline_arrow_right_24), contentDescription = null )
                            Text(
                                text = "full-stack web development",
                                Modifier.padding(bottom = 8.dp, start = 8.dp)
                            )
                        }
                        Row {
                            Icon(painterResource(id = R.drawable.baseline_arrow_right_24), contentDescription = null )
                            Text(
                                text = "hybrid mobile application development",
                                Modifier.padding(bottom = 8.dp, start = 8.dp)
                            )
                        }
                        Row {
                            Icon(painterResource(id = R.drawable.baseline_arrow_right_24), contentDescription = null )
                            Text(
                                text = "native mobile application development",
                                Modifier.padding(bottom = 8.dp, start = 8.dp)
                            )
                        }
                    }

                }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenComposable(){
    HomeScreen()
}