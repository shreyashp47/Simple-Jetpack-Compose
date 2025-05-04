package com.shreyash.simplecompose.presentation

import androidx.lifecycle.ViewModel
import com.shreyash.simplecompose.di.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {
    
    fun getHiltMessages(): List<String> {
        return messageRepository.getMessages()
    }
}