package com.example.bsuirmentors.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Schedules(
    @SerializedName("Вторник")
    val вторник: List<Day>,
    @SerializedName("Понедельник")
    val понедельник: List<Day>,
    @SerializedName("Пятница")
    val пятница: List<Day>,
    @SerializedName("Среда")
    val среда: List<Day>,
    @SerializedName("Суббота")
    val суббота: List<Day>,
    @SerializedName("Четверг")
    val четверг: List<Day>
)