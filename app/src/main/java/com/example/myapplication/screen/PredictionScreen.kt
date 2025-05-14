package com.example.myapplication.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.presentation.PredictionViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

// Composable function for the main prediction screen UI
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitanicPredictionScreen(viewModel: PredictionViewModel = viewModel()) {
    Scaffold(
        topBar = {
            // Top app bar with the screen title
            TopAppBar(title = { Text("Titanic Survival Prediction") })
        }
    ) { paddingValues ->
        // Main content column
        Column(
            modifier = Modifier
                .padding(paddingValues) // Apply padding from the scaffold
                .padding(16.dp) // Add internal padding
                .fillMaxSize(), // Fill the available space
            horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally
            verticalArrangement = Arrangement.spacedBy(8.dp) // Add vertical spacing between elements
        ) {
            // Input field for Passenger Class
            OutlinedTextField(
                value = viewModel.passengerClass,
                onValueChange = { viewModel.passengerClass = it },
                label = { Text("Passenger Class (1, 2, or 3)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Set keyboard type to number
            )
            // Input field for Sex
            OutlinedTextField(
                value = viewModel.sex,
                onValueChange = { viewModel.sex = it },
                label = { Text("Sex (male or female)") }
            )
            // Input field for Age
            OutlinedTextField(
                value = viewModel.age,
                onValueChange = { viewModel.age = it },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Set keyboard type to number
            )
            // Input field for Number of Siblings/Spouses
            OutlinedTextField(
                value = viewModel.siblingsSpouses,
                onValueChange = { viewModel.siblingsSpouses = it },
                label = { Text("Number of Siblings/Spouses") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Set keyboard type to number
            )
            // Input field for Number of Parents/Children
            OutlinedTextField(
                value = viewModel.parentsChildren,
                onValueChange = { viewModel.parentsChildren = it },
                label = { Text("Number of Parents/Children") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Set keyboard type to number
            )

            Spacer(modifier = Modifier.height(16.dp)) // Add vertical space

            // Button to trigger prediction
            Button(
                onClick = { viewModel.predictSurvival() }, // Call ViewModel function on click
                enabled = !viewModel.isLoading // Disable button while loading
            ) {
                // Button text changes based on loading state
                Text(if (viewModel.isLoading) "Predicting..." else "Predict Survival")
            }

            // Display prediction result if available
            viewModel.predictionResult?.let { result ->
                Text(
                    text = result,
                    style = MaterialTheme.typography.headlineSmall, // Apply headline small text style
                    modifier = Modifier.padding(top = 16.dp) // Add top padding
                )
            }

            // Display error message if available
            viewModel.errorMessage?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error, // Set text color to error color
                    modifier = Modifier.padding(top = 8.dp) // Add top padding
                )
            }
        }
    }
}

// Preview function for the Composable
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TitanicPredictionScreen()
}
