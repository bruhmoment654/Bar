package com.example.cocktailbar

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.cocktailbar.ui.common.BaseViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <S, E> LifecycleOwner.subscribe(
    viewModel: BaseViewModel<S, E>,
    onNewState: (S) -> Unit,
    onNewEffect: (E) -> Unit = {}
) {
    lifecycleScope.launchWhenResumed {
        launch {
            viewModel.state.collectLatest {
                onNewState(it)
            }
        }

        launch {
            viewModel.effects.collectLatest {
                onNewEffect(it)
            }
        }
    }
}