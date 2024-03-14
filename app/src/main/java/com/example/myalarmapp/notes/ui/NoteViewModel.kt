package com.example.myalarmapp.notes.ui

import com.example.myalarmapp.common.BaseViewModel
import com.example.myalarmapp.notes.data.Note
import com.example.myalarmapp.notes.data.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository
) : BaseViewModel<Note>(repository)

