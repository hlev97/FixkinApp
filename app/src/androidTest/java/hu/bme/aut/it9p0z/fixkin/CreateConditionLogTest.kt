package hu.bme.aut.it9p0z.fixkin

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import hu.bme.aut.conditionlog_domain.usecases.SaveConditionLogUseCase
import hu.bme.aut.conditionlog_ui.create.CreateConditionLogScreen
import hu.bme.aut.conditionlog_ui.create.CreateConditionLogViewModel
import hu.bme.aut.it9p0z.conditionlog_data.repository.ConditionLogRepository
import hu.bme.aut.it9p0z.database.DatabaseDatasourceImpl
import hu.bme.aut.it9p0z.database.daos.ConditionLogDao
import hu.bme.aut.it9p0z.database.daos.SurveyLogDao
import hu.bme.aut.it9p0z.database.datasource.DatabaseDatasource
import hu.bme.aut.it9p0z.network.datasource.NetworkDatasource
import hu.bme.aut.it9p0z.preferences.datasource.PreferencesDatasource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@OptIn(
    ExperimentalMaterial3Api::class,
)
@HiltAndroidTest
class CreateConditionLogTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createComposeRule()

    @Inject lateinit var skinConditionLogDao: ConditionLogDao
    @Inject lateinit var surveyLogDao: SurveyLogDao

    private lateinit var databaseDatasource: DatabaseDatasource

    @Inject lateinit var networkDatasource: NetworkDatasource

    @Inject lateinit var preferencesDatasource: PreferencesDatasource

    private lateinit var repository: ConditionLogRepository

    private lateinit var context: Context

    lateinit var saveConditionLogUseCase: SaveConditionLogUseCase

    private lateinit var viewModel: CreateConditionLogViewModel

    @Before
    fun setUp() = runBlocking {
        hiltRule.inject()
        databaseDatasource = DatabaseDatasourceImpl(skinConditionLogDao,surveyLogDao)

        repository = ConditionLogRepository(
            preferencesDatasource,
            networkDatasource,
            databaseDatasource
        )

        context = ApplicationProvider.getApplicationContext()

        saveConditionLogUseCase = SaveConditionLogUseCase(repository,context)

        viewModel = CreateConditionLogViewModel(saveConditionLogUseCase,context)

        composeRule.setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                CreateConditionLogScreen(
                    onFabClick = { },
                    onBack = { },
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        preferencesDatasource.saveUsername("hlev97")
        preferencesDatasource.savePassword("password")
    }

    @After
    fun tearDown() = runBlocking {
        preferencesDatasource.resetDates()
    }

    // Before running this test be sure that the emulator has access to the Internet
    @Test
    fun fillOutSkinConditionLog_andSaveIt() = runBlocking {
        val userInfo = preferencesDatasource.loadUserInfo().first()
        val logsSizeBefore = networkDatasource
            .getAllConditionLogs(userInfo.userName,userInfo.password)
            .getOrNull()?.size ?: 0

        composeRule
            .onNodeWithTag("feelingSlider")
            .performTouchInput {
                click()
            }

        composeRule
            .onNodeWithTag("milk")
            .performClick()
            .assertIsSelected()

        composeRule
            .onNodeWithTag("rainy")
            .performClick()
            .assertIsSelected()

        composeRule
            .onNodeWithTag("cold")
            .performClick()
            .assertIsSelected()

        composeRule
            .onNodeWithTag("saveFab")
            .performClick()

        delay(3000)

        val logsSizeAfter = networkDatasource
            .getAllConditionLogs(userInfo.userName,userInfo.password)
            .getOrNull()?.size ?: 0

        assertThat(logsSizeAfter).isGreaterThan(logsSizeBefore)
    }

    // Before running this test turn off internet access on the emulator (both WIFI and Mobile data)
    @Test
    fun fillOutSkinConditionLog_andSaveIt_WithoutInternetAccess() = runBlocking {
        val logsSizeBefore = databaseDatasource.getNumberOfConditionLogs()

        composeRule
            .onNodeWithTag("feelingSlider")
            .performTouchInput {
                click()
            }

        composeRule
            .onNodeWithTag("milk")
            .performClick()
            .assertIsSelected()

        composeRule
            .onNodeWithTag("rainy")
            .performClick()
            .assertIsSelected()

        composeRule
            .onNodeWithTag("cold")
            .performClick()
            .assertIsSelected()

        composeRule
            .onNodeWithTag("saveFab")
            .performClick()

        delay(3000)

        val logsSizeAfter = databaseDatasource.getNumberOfConditionLogs()
        assertThat(logsSizeAfter).isGreaterThan(logsSizeBefore)
    }
}