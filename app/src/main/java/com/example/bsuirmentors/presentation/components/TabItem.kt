package com.example.bsuirmentors.presentation.components

import androidx.compose.runtime.Composable
import com.example.bsuirmentors.presentation.groupList.components.GroupListScreen
import com.example.bsuirmentors.presentation.mentorList.components.MentorListScreen

typealias ComposableFun = @Composable () -> Unit
sealed class TabItem(var title: String, var screen: ComposableFun) {

    object Groups: TabItem("Группы", { GroupListScreen() })
    object Mentors: TabItem("Преподаватели", { MentorListScreen() })
}
