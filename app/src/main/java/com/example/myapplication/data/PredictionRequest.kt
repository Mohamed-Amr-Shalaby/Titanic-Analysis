package com.example.myapplication.data

import com.google.gson.annotations.SerializedName

data class PredictionRequest(
    @SerializedName("passenger_class") val passengerClass: Int,
    @SerializedName("sex") val sex: String,
    @SerializedName("age") val age: Int,
    @SerializedName("siblings_spouses") val siblingsSpouses: Int,
    @SerializedName("parents_children") val parentsChildren: Int
)

// Define the data class for the response body
data class PredictionResponse(
    @SerializedName("survival_chance") val survivalChance: Double
)