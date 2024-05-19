package com.tpanh.jobfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.model.WorkExperience
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkExperienceViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(WorkExperience())
    val uiState = _uiState.asStateFlow()

    fun updateJobTitle(jobTitle: String) {
        _uiState.update {
            it.copy(jobTitle = jobTitle)
        }
    }

    fun updateCompany(company: String) {
        _uiState.update {
            it.copy(company = company)
        }
    }

    fun updateStartDay(startDay: Long) {
        _uiState.update {
            it.copy(startDay = startDay)
        }
    }

    fun updateEndDay(endDay: Long) {
        _uiState.update {
            it.copy(endDay = endDay)
        }
    }

    fun updateDescription(description: String) {
        _uiState.update {
            it.copy(description = description)
        }
    }

    fun updateIsCurrentWorking(isCurrentWorking: Boolean) {
        _uiState.update {
            it.copy(isCurrentWorking = isCurrentWorking)
        }
    }

    fun addWorkExperience(userId: String) {
        viewModelScope.launch {
            userRepository.addWorkExperience(userId, _uiState.value)
        }
    }
}