package hu.bme.aut.it9p0z.fixkin.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.it9p0z.fixkin.navigation.graphs.Graph
import hu.bme.aut.it9p0z.preferences.datasource.PreferencesDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val preferencesDatasource: PreferencesDatasource
): ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Graph.AUTH)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val showAuth = preferencesDatasource.loadShowAuthentication().first()
            if (showAuth) {
                _startDestination.value = Graph.AUTH
            } else _startDestination.value = Graph.HOME
        }
        _isLoading.value = false
    }

}