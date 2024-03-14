package com.example.myalarmapp.common

import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {
    suspend fun insert(item: T)
    suspend fun getAll(): Flow<List<T>>
    suspend fun update(item: T)
    suspend fun delete(item: T)
    suspend fun getById(id: Long): T?
}