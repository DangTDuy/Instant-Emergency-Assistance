package com.example.resqnow.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.resqnow.Repository.Repository
import com.example.resqnow.Room.ContactDao
import com.example.resqnow.Room.ContactEntity
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ContactViewModel(private val repository: Repository) : ViewModel() {
    private val _contacts = MutableStateFlow<List<ContactEntity>>(emptyList())
    val contacts: StateFlow<List<ContactEntity>> = _contacts

    private val _selectedContact = MutableStateFlow<ContactEntity?>(null)
    val selectedContact: StateFlow<ContactEntity?> = _selectedContact

    init {
        loadContacts()
    }

    // Lấy tất cả các liên hệ
    fun loadContacts() {
        viewModelScope.launch {
            repository.getAllContact().collect { contacts ->
                _contacts.value = contacts
            }
        }
    }

    // Lấy liên hệ theo ID
    fun loadContactById(id: Int) {
        viewModelScope.launch {
            _selectedContact.value = repository.getContactById(id)
        }
    }
    fun getContactsByUserId(userId: String): Flow<List<ContactEntity>> {
        return repository.getContactsByUserId(userId)
    }

    fun addContact(contact: ContactEntity) {
        viewModelScope.launch {
            repository.addContact(contact)
            loadContacts() // Tải lại danh bạ sau khi thêm liên hệ mới
        }
    }

    fun deleteContact(contact: ContactEntity) {
        viewModelScope.launch {
            repository.deleteContact(contact)
            loadContacts() // Tải lại danh bạ sau khi xóa liên hệ
        }
    }
    // Factory class để cung cấp các dependencies cho ContactViewModel
    class ContactViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ContactViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
