package com.tpanh.jobfinder.sreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.tpanh.jobfinder.navigation.JobFinderNavigation
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun AddEducationTopBar() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled .ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEducationContent() {

    var levelOfEducation by remember { mutableStateOf("") }
    var institutionName by remember { mutableStateOf("") }
    var fieldOfStudy by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isCurrentlyStudying by remember { mutableStateOf(false) }

    val format = SimpleDateFormat("dd MMM yyyy")

    var startDate by remember { mutableStateOf(format.format(System.currentTimeMillis())) }
    var isStartDatePickerVisible by remember { mutableStateOf(false) }

    var endDate by remember { mutableStateOf(format.format(System.currentTimeMillis())) }
    var isEndDatePickerVisible by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Add Education",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Level of Education",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = levelOfEducation,
            onValueChange = { levelOfEducation = it },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Institution Name",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = institutionName,
            onValueChange = { institutionName = it },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Field of Study",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = fieldOfStudy,
            onValueChange = { fieldOfStudy = it },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column (
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Start Date",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = { isStartDatePickerVisible = true },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(text = startDate)
                }
                if (isStartDatePickerVisible) {
                    val datePickerState = rememberDatePickerState()
                    val confirmEnable = derivedStateOf { datePickerState.selectedDateMillis != null }

                    DatePickerDialog(
                        onDismissRequest = { isStartDatePickerVisible = false },
                        confirmButton = {
                            TextButton(onClick = {
                                isStartDatePickerVisible = false
                                var date = "No Selection"
                                if (datePickerState.selectedDateMillis != null) {
                                    date = format.format(Date(datePickerState.selectedDateMillis!!))
                                }
                                startDate = date
                            },
                                enabled = confirmEnable.value
                            ) {
                                Text("Ok")
                            }
                        }) {
                        DatePicker(state = datePickerState)
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column (
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "End Date",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { isEndDatePickerVisible = true }
                ) {
                    Text(text = endDate)
                }
                if (isEndDatePickerVisible) {
                    val datePickerState = rememberDatePickerState()
                    val confirmEnable = derivedStateOf { datePickerState.selectedDateMillis != null }

                    DatePickerDialog(
                        properties = DialogProperties(),
                        onDismissRequest = { isEndDatePickerVisible = false },
                        confirmButton = {
                            TextButton(onClick = {
                                isEndDatePickerVisible = false
                                var date = "No Selection"
                                if (datePickerState.selectedDateMillis != null) {
                                    date = format.format(Date(datePickerState.selectedDateMillis!!))
                                }
                                endDate = date
                            },
                                enabled = confirmEnable.value
                            ) {
                                Text("Ok")
                            }
                        }) {
                        DatePicker(state = datePickerState)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Checkbox(
                checked = isCurrentlyStudying,
                onCheckedChange = { isCurrentlyStudying = it })
            Text(text = "I am currently studying here", color = MaterialTheme.colorScheme.onPrimaryContainer)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Description",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight(700)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            value = description,
            onValueChange = { description = it },
            singleLine = false,
            placeholder = {
                Text(
                    text = "Write additional information here",
                    fontSize = 12.sp
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .padding(horizontal = 70.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = "SAVE",
                    letterSpacing = 2.sp,
                )
            }
        }
    }
}

@Composable
fun AddEducation() {
    Scaffold (
        topBar = {
            AddEducationTopBar()
        }
    ) { innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)) {
            AddEducationContent()
        }
    }
}

@Preview
@Composable
fun AddEducationPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        AddEducation()
    }
}