package com.example.maddapp.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.maddapp.model.CoursesRepository
import com.example.maddapp.ui.screen.Course
import java.util.concurrent.Flow

class CourseViewModel(courseId:String):ViewModel() {
   private val coursesRepository = CoursesRepository()
    val courseInfo: Course = coursesRepository.getCourseInfo(courseId)
}