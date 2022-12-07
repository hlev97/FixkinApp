package hu.bme.aut.it9p0z.fixkin.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.fixkin.DateUtil
import hu.bme.aut.it9p0z.preferences.datasource.PreferencesDatasource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val preferencesDatasource: PreferencesDatasource,
    private val databaseDatasource: DatabaseDatasource
): ViewModel() {

    private val _createConditionLogEnabled: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val createConditionLogEnabled: StateFlow<Boolean> = _createConditionLogEnabled.asStateFlow()

    private val _createSurveyLogEnabled: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val createSurveyLogEnabled: StateFlow<Boolean> = _createSurveyLogEnabled.asStateFlow()

    init {
        loadLastDates()
    }

    fun loadLastDates() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            val lastConditionLogDate = preferencesDatasource.loadLastConditionLogDate().catch {
                _createConditionLogEnabled.emit(true)
            }.first()

            Log.i("result", lastConditionLogDate)

            _createConditionLogEnabled.emit(if (lastConditionLogDate == "") {
                true
            } else DateUtil.aDayPassed(LocalDate.parse(lastConditionLogDate, DateUtil.formatter)))

            val lastSurveyLogDate = preferencesDatasource.loadLastSurveyLogDate().catch {
                _createSurveyLogEnabled.emit(true)
            }.first()

            Log.i("result", lastSurveyLogDate)

            _createSurveyLogEnabled.emit(if (lastSurveyLogDate == "") {
                true
            } else DateUtil.aDayPassed(LocalDate.parse(lastSurveyLogDate, DateUtil.formatter)))
        }
    }

    fun logOut() {
        viewModelScope.launch {
            preferencesDatasource.savePassword("")
            preferencesDatasource.saveUsername("")
            preferencesDatasource.resetDates()
            databaseDatasource.deleteAllConditionLogs()
            databaseDatasource.deleteAllSurveyLogs()
            preferencesDatasource.saveShowAuthentication(true)
        }
    }

}