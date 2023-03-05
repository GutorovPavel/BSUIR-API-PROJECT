package com.example.bsuirmentors.domain.models

data class Group(
//    val calendarId: String,
//    val course: Int,
//    val educationDegree: Int,
//    val facultyAbbrev: String,
//    val facultyId: Int,
//    val id: Int,
    val name: String,
//    val specialityAbbrev: String,
//    val specialityDepartmentEducationFormId: Int,
//    val specialityName: String
) {
    fun doesMatchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            name,
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}
