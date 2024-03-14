package com.example.myalarmapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<T>(private val repository: BaseRepository<T>) :
    ViewModel() {
    private val _items: MutableStateFlow<List<T>> = MutableStateFlow(emptyList())
    val items: StateFlow<List<T>> = _items

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            repository.getAll().collect { items ->
                _items.value = items
            }
        }
    }

    fun getItemById(id: Long): T? {
        var item: T? = null
        viewModelScope.launch {
            item = repository.getById(id)
        }
        return item
    }

    fun insertItem(item: T) {
        viewModelScope.launch {
            repository.insert(item)
        }
    }

    fun updateItem(item: T) {
        viewModelScope.launch {
            repository.update(item)
        }
    }

    fun deleteItem(item: T) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }
}
