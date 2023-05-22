package com.example.maddapp.model

import com.example.maddapp.ui.screen.Course


class CoursesRepository(){
    private val courses:Map<String, Course> = mapOf(
        "mad9013" to  Course("mad9013", "Web Dev"),
        "mad9014" to Course("mad9014", "Javascript"),
        "mad9011" to Course("mad9011", "Design"),

        "mad9022" to Course("mad9022", "FrontEnd"),
        "mad9124" to Course("mad9124", "BackEnd"),
        "mad9020" to Course("mad9020", "UI"),

        "mad9135" to Course("mad9135", "React"),
        "mad9137" to Course("mad9137", "IOS"),
        "mad9136" to Course("mad9136", "Android"),

        "mad9145" to Course("mad9145", "Applied Project"),
        "mad9143" to Course("mad9143", "Marketing"),
        "mad9146" to Course("mad9146", "Maui"),
    )

    fun getCourseInfo(courseId: String):Course{
       return courses[courseId]?:throw Exception("Course not found")
    }
}