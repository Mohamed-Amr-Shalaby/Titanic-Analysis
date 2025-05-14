package com.example.myapplication.presentation


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.PredictionRequest
import com.example.myapplication.data.RetrofitInstance
import kotlinx.coroutines.launch

// ViewModel to handle the state and API calls
class PredictionViewModel : ViewModel() {
    // State variables for the UI input fields
    var passengerClass by mutableStateOf("")
    var sex by mutableStateOf("")
    var age by mutableStateOf("")
    var siblingsSpouses by mutableStateOf("")
    var parentsChildren by mutableStateOf("")

    // State variables for the prediction result, loading status, and error message
    var predictionResult by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    // Function to trigger the survival prediction
    fun predictSurvival() {
        // Basic input validation
        if (passengerClass.isBlank() || sex.isBlank() || age.isBlank() || siblingsSpouses.isBlank() || parentsChildren.isBlank()) {
            errorMessage = "Please fill in all fields."
            return
        }

        // Attempt to convert input strings to numbers
        val pClass = passengerClass.toIntOrNull()
        val pAge = age.toIntOrNull()
        val pSiblingsSpouses = siblingsSpouses.toIntOrNull()
        val pParentsChildren = parentsChildren.toIntOrNull()

        // Validate numeric inputs
        if (pClass == null || pAge == null || pSiblingsSpouses == null || pParentsChildren == null) {
            errorMessage = "Please enter valid numbers for numeric fields."
            return
        }

        // Clear previous error message and result
        errorMessage = null
        predictionResult = null

        // Set loading state to true
        isLoading = true

        // Launch a coroutine to perform the API call
        viewModelScope.launch {
            try {
                // Create the request body
                val request = PredictionRequest(
                    passengerClass = pClass,
                    sex = sex.trim().lowercase(), // Ensure consistent format for sex
                    age = pAge,
                    siblingsSpouses = pSiblingsSpouses,
                    parentsChildren = pParentsChildren
                )
                // Make the API call
                val response = RetrofitInstance.api.predict(request)
                // Format and update the prediction result
                predictionResult = "Survival Chance: %.2f%%".format(response.survivalChance * 100)
            } catch (e: Exception) {
                // Handle errors and update the error message
                errorMessage = "Error: ${e.message}"
                e.printStackTrace() // Print stack trace for debugging
            } finally {
                // Set loading state back to false
                isLoading = false
            }
        }
    }
}
