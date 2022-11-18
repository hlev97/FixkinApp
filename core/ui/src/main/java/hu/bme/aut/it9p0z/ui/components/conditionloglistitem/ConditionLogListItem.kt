package hu.bme.aut.it9p0z.ui.components.conditionloglistitem

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.bme.aut.it9p0z.ui.data.FeelingUi
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.foodTriggerChips
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.mentalTriggerChips
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.otherTriggerChips
import hu.bme.aut.it9p0z.ui.data.UiTrigger.Companion.weatherTriggerChips
import hu.bme.aut.it9p0z.ui.model.ConditionLogListItemModel
import hu.bme.aut.it9p0z.ui.model.UiChip
import hu.bme.aut.it9p0z.ui.model.UiText
import hu.bme.aut.it9p0z.ui.theme.*
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import me.saket.swipe.rememberSwipeableActionsState

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun ConditionLogListItem(
    listItem: ConditionLogListItemModel,
    lastItem: Boolean = false,
    onDelete: () -> Unit,
    onEdit: () -> Unit,
) {
    val context = LocalContext.current
    val state = rememberSwipeableActionsState()

    val delete = SwipeAction(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = null,
                modifier = Modifier
                    .padding(dp_l)
                    .rotate(-state.offset.value / 20),
                tint = MaterialTheme.colorScheme.onSwipeToDelete
            )
        },
        background = MaterialTheme.colorScheme.swipeToDelete,
        onSwipe = onDelete
    )

    val edit = SwipeAction(
        icon = {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = null,
                modifier = Modifier
                    .padding(dp_l)
                    .rotate(-state.offset.value / 20),
                tint = MaterialTheme.colorScheme.onSwipeToEdit
            )
        },
        background = MaterialTheme.colorScheme.swipeToEdit,
        onSwipe = onEdit
    )
    SwipeableActionsBox(
        swipeThreshold = 100.dp,
        backgroundUntilSwipeThreshold = MaterialTheme.colorScheme.listItemBackgroundThreshold,
        state = state,
        startActions = listOf(delete),
        endActions = listOf(edit)
    ) {
        ListItem(
            headlineText = {
                Text(
                    text = listItem.date.asString(context),
                    modifier = Modifier.padding(bottom = dp_m),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            supportingText = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    listItem.triggers.subList(0,3).forEach { trigger ->
                        TriggerUiChip(trigger = trigger)
                    }
                    Icon(imageVector = Icons.Outlined.MoreHoriz, contentDescription = null)
                }
            },
            leadingContent = {
                listItem.feeling.icon.AsImage()
            }
        )
        if (!lastItem) {
            Divider()
        }
    }

}

@ExperimentalMaterial3Api
@Composable
fun TriggerUiChip(
    trigger: UiChip
) {
    val context = LocalContext.current
    Surface(
        shape = FilterChipDefaults.shape,
        color = MaterialTheme.colorScheme.secondaryContainer,
        tonalElevation = 1.dp,
        shadowElevation = 1.dp,
        modifier = Modifier.padding(bottom = dp_m)
    ) {
        Row(
            Modifier
                .defaultMinSize(minHeight = FilterChipDefaults.Height)
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (trigger.icon != null) {
                trigger.icon.AsImage(modifier = Modifier.size(18.0.dp))
            }
            Spacer(Modifier.width(8.dp))
            Text(
                text = trigger.label.asString(context),
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(Modifier.width(8.dp))
        }
    }
    Spacer(Modifier.width(8.dp))
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun ConditionLogListItem_Preview() {
    val uiChips = mutableListOf<UiChip>()
    uiChips.addAll(foodTriggerChips)
    uiChips.addAll(weatherTriggerChips)
    uiChips.addAll(mentalTriggerChips)
    uiChips.addAll(otherTriggerChips)
    FixkinTheme {
        val state = rememberScrollState()
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(state)) {
            for (i in 0..8) {
                ConditionLogListItem(
                    listItem = ConditionLogListItemModel(
                        id = 0,
                        date = UiText.DynamicString("2022/10/07"),
                        feeling = FeelingUi.Sad,
                        triggers = uiChips
                    ),
                    onDelete = { },
                    onEdit = { }
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ConditionLogListItem_PreviewDark() {
    val uiChips = mutableListOf<UiChip>()
    uiChips.addAll(foodTriggerChips)
    uiChips.addAll(weatherTriggerChips)
    uiChips.addAll(mentalTriggerChips)
    uiChips.addAll(otherTriggerChips)
    FixkinTheme {
        val state = rememberScrollState()
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(state)) {
            for (i in 0..8) {
                ConditionLogListItem(
                    listItem = ConditionLogListItemModel(
                        id = 0,
                        date = UiText.DynamicString("2022/10/07"),
                        feeling = FeelingUi.Sad,
                        triggers = uiChips
                    ),
                    onDelete = { },
                    onEdit = { }
                )
            }
        }
    }
}