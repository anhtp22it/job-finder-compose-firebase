package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.di.AppContainer
import com.tpanh.jobfinder.model.Education
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddEducationViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(Education())
    val uiState: StateFlow<Education> = _uiState.asStateFlow()

    init {
        getCurrentEducation()
    }

    fun updateLevelOfEducation(levelOfEducation: String) {
        _uiState.update { currentState ->
            currentState.copy(levelOfEducation = levelOfEducation)
        }
    }

    fun updateInstitutionName(institutionName: String) {
        _uiState.update { currentState ->
            currentState.copy(institutionName = institutionName)
        }
    }

    fun updateFieldOfStudy(fieldOfStudy: String) {
        _uiState.update { currentState ->
            currentState.copy(fieldOfStudy = fieldOfStudy)
        }
    }

    fun updateDescription(description: String) {
        _uiState.update { currentState ->
            currentState.copy(description = description)
        }
    }

    fun updateIsCurrentlyStudying(isCurrentlyStudying: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(currentlyStudying = isCurrentlyStudying)
        }
    }

    fun updateStartDate(startDate: Long) {
        _uiState.update { currentState ->
            currentState.copy(startDate = startDate)
        }
    }

    fun updateEndDate(endDate: Long) {
        _uiState.update { currentState ->
            currentState.copy(endDate = endDate)
        }
    }

    fun saveEducation(navigateToViewProfile: () -> Unit) {
        viewModelScope.launch {
            userRepository.updateEducation(_uiState.value)
        }
        navigateToViewProfile()
    }

    private fun getCurrentEducation() {
        viewModelScope.launch {
            _uiState.value = userRepository.getCurrentUser().education
        }
    }
}