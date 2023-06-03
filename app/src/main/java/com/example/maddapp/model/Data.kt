package com.example.maddapp.model

import com.example.maddapp.ui.screen.Course


class CoursesRepository {
    private val courses:Map<String, Course> = mapOf(
        "mad9013" to  Course("mad9013","Web Dev", "Technologies on the web evolve quickly. Every year brings new devices and with them new capabilities. These devices present many challenges and opportunities to web developers. Students review fundamentals of web development using hypertext markup language (HTML), and cascading style sheets (CSS), with a focus on developing responsive and mobile websites. Multiple IDEs are introduced and used to complete hands-on projects."),
        "mad9014" to Course("mad9014", "Javascript", "While developing native applications for mobile devices holds many advantages, not every application requires direct access to native capabilities and there are many ways web developers can build applications using the programming skills they already possess. Students use HTML, CSS and Javascript to build applications which can quickly be deployed to multiple mobile platforms, such as iPhone and Android. Designing to conserve battery life is introduced as a concept in this course. Using mobile marketplaces to publish and market applications is introduced."),
        "mad9011" to Course("mad9011", "Design", "Students are introduced to mobile design fundamentals and graphical user interface design tools. The design concepts include user interface visual elements, principles, accessibility and usability. Students gain production experience and skills implementing mobile industry-standard graphics and design tools, which help them create effective interfaces for mobile-first web and mobile applications. Students apply hands-on learning with different software packages to create visual elements, icons, splash graphics, scalable vector graphic (SVG) animations and wireframes for mobile user interfaces."),

        "mad9022" to Course("mad9022", "FrontEnd","Students expand on their web development skills from the first semester, while learning how to create a variety of web and mobile projects. Full Stack, JAMStack, Progressive Web Apps, and Hybrid Apps are explored as viable solutions for modern app projects. Maintenance requirements and the limitations of different platforms are also examined."),
        "mad9124" to Course("mad9124", "BackEnd", "Students enhance their JavaScript skills to become productive with Full-stack development. They use a hands-on approach to build APIs using Node.JS and a variety of tools, frameworks, libraries and packages. The creation of these modern APIs also requires the students to develop skills with persistent scalable database storage systems. Project work culminates with students creating APIs to be used with websites and mobile applications. Students work individually as well as with other students to complete tasks."),
        "mad9020" to Course("mad9020", "UI", "Students learn how to create functional, efficient and enjoyable interfaces. They gain an appreciation for the challenges of building websites and applications that remain functional across different devices and platforms. Usability that allows people to complete tasks on any device, any time, is emphasized. Focus is placed on practical application of students' design software experience in the creation of mobile application interfaces."),

        "mad9135" to Course("mad9135", "React", "Students leverage their full-stack JavaScript skills acquired in previous client-side and server-side development to build dynamic JavaScript Applications for both web and native (iOS and Android) platforms. They learn how to use frameworks, such as React and React Native, along with numerous task automation and productivity tools, while completing hands-on projects. Students also explore best practices for integrating automated testing tools into their development workflow."),
        "mad9137" to Course("mad9137", "IOS", "Students use the Swift language within the standard Mac OSX development environment to create native applications for the iPhone and iPad. Working together in teams, students build and test applications. User interface requirements and building for a better user experience is stressed."),
        "mad9136" to Course("mad9132", "Android", "Students learn to develop mobile applications for the Android mobile platform. Students use the Android application programming interface (API) and Android software development kit (SDK) for hands-on development of deployable mobile applications. Designing to conserve battery life on mobile devices is emphasized."),

        "mad9145" to Course("mad9145", "Applied Project", "Working in teams, students experience the analysis, design, implementation, testing and deployment of a mobile solution for a real-world client. Important topics from throughout their program of study are applied in this course. Faculty advisors facilitate student teams to demonstrate their skills in the applied project. Student teams make a technical presentation to their faculty advisors and participate in a public showcase of projects."),
        "mad9143" to Course("mad9143", "Marketing", "The mobile landscape from a business point of view is discussed. Topics to be covered include business planning, revenue models, analytics, as well as other entrepreneurial skills. Students work together to explore the importance of networking within the context of mobile development projects."),
        "mad9146" to Course("mad9146", "Maui", "Windows development can be accomplished through a variety of languages. Leveraging prior skills in creating mobile applications with object-oriented concepts, students focus on developing Windows mobile applications using XAML and C#. Designing to conserve battery life on mobile devices is stressed."),
    )

    fun getCourseInfo(courseId: String):Course{
       return courses[courseId]?:throw Exception("Course not found")
    }
}

data class MyTech(
    val id:String,
    val name:String,
    val company: String,
    val yearOfCreation:String
)





