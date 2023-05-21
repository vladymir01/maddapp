package com.example.maddapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.maddapp.model.CoursesRepository
import java.util.concurrent.Flow

class CourseViewModel(
    savedStateHandle: SavedStateHandle,
    private  val coursesRepository: CoursesRepository
):ViewModel() {
     val courseId:String = checkNotNull(savedStateHandle["item"])
     val courseInfo:Course = coursesRepository.getCourseInfo(courseId)
}