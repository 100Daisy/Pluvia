package com.OxGames.Pluvia.ui.component.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alorma.compose.settings.ui.base.internal.LocalSettingsGroupEnabled
import com.alorma.compose.settings.ui.base.internal.SettingsTileScaffold

@Composable
fun SettingsListDropdown(
    modifier: Modifier = Modifier,
    enabled: Boolean = LocalSettingsGroupEnabled.current,
    value: Int,
    items: List<String>,
    onItemSelected: (Int) -> Unit,
    title: @Composable () -> Unit,
    subtitle: (@Composable () -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    action: @Composable (() -> Unit)? = null,
) {
    if (value > items.size) {
        throw IndexOutOfBoundsException("Current value of state for list setting cannot be greater than items size")
    }

    var isDropdownExpanded by remember { mutableStateOf(false) }

    SettingsTileScaffold(
        modifier = Modifier.clickable(
            enabled = enabled,
            onClick = { isDropdownExpanded = true },
        ).then(modifier),
        enabled = enabled,
        title = title,
        subtitle = subtitle,
        icon = icon,
    ) {
        Row {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(128.dp),
                text = items[value],
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.End,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier.width(16.dp))
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                imageVector = if (isDropdownExpanded)
                    Icons.Filled.ArrowDropUp
                else
                    Icons.Filled.ArrowDropDown,
                contentDescription = "Dropdown arrow",
            )
            if (action != null) {
                Spacer(modifier.width(16.dp))
                action()
            }
        }

        DropdownMenu(
            expanded = isDropdownExpanded,
            onDismissRequest = { isDropdownExpanded = false },
        ) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(
                    enabled = enabled,
                    text = { Text(text = text) },
                    onClick = {
                        onItemSelected(index)
                        isDropdownExpanded = false
                    },
                )
            }
        }
    }
}
