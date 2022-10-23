package hu.bme.aut.it9p0z.history_ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import hu.bme.aut.it9p0z.ui.components.conditionloglistitem.ConditionLogListItem

@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
    onEdit: (Int) -> Unit
) {

    val state by viewModel.state.collectAsState()
    val data by viewModel.result.observeAsState()

    val context = LocalContext.current

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = viewModel.isRefreshing),
        onRefresh = { viewModel.refresh() },
        modifier = modifier.fillMaxSize()
    ) {
        when(state) {
            is Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is DataReady -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(data ?: emptyList()) { log ->
                        ConditionLogListItem(
                            listItem = log,
                            onDelete = { viewModel.deleteLog(log.id) },
                            onEdit = { onEdit(log.id) }
                        )
                    }
                }
            }
            is Error -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = (state as Error).message.asString(context))
                }
            }
        }
    }
}