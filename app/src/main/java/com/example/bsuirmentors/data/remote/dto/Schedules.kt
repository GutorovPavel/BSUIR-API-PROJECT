package com.example.bsuirmentors.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Schedules(
    @SerializedName("Вторник")
    val вторник: List<Вторник>,
    @SerializedName("Понедельник")
    val понедельник: List<Понедельник>,
    @SerializedName("Пятница")
    val пятница: List<Пятница>,
    @SerializedName("Среда")
    val среда: List<Среда>,
    @SerializedName("Суббота")
    val суббота: List<Суббота>,
    @SerializedName("Четверг")
    val четверг: List<Четверг>
)