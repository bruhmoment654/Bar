package com.example.cocktailbar.ui.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S, E>(application: Application) : AndroidViewModel(application) {

    private val _state by lazy { MutableStateFlow(initialState) }
    val state: StateFlow<S>
        get() = _state.asStateFlow()

    private val _effects = Channel<E>(Channel.BUFFERED)
    val effects: Flow<E>
        get() = _effects.receiveAsFlow()


    abstract val initialState: S


    protected fun setState(block: S.() -> S) = viewModelScope.launch {
        val currentState = state.value
        _state.emit(block(currentState))
    }

    fun emitEffect(effect: E) = viewModelScope.launch {
        effect.emit()
    }

    protected fun E.emit() = viewModelScope.launch {
        _effects.send(this@emit)
    }
}