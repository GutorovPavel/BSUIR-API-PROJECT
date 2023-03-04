package com.example.bsuirmentors.domain.models

data class Mentor(
    val academicDepartment: List<String>,
//    val calendarId: String,
    val degree: String,
//    val fio: String,
    val firstName: String,
//    val id: Int,
    val lastName: String,
    val middleName: String,
    val photoLink: String,
    val rank: String? = "",
    val urlId: String
) {
    fun doesMatchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
//            "$firstName$lastName",
//            "$firstName $lastName",
//            "$lastName$firstName",
//            "$lastName $firstName",
            "$lastName$firstName$middleName",
            "$lastName $firstName $middleName",
            "$rank",
            "${lastName.first()}${firstName.first()}",
            "${firstName.first()}${middleName.first()}"
        )

        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}