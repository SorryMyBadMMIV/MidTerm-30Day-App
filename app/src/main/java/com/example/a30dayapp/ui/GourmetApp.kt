package com.example.a30dayapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.a30dayapp.R
import com.example.a30dayapp.data.Datasource
import com.example.a30dayapp.model.Restaurant

/**
 * The whole screen. It holds no state of its own: [openedDays] comes in from
 * the caller, and [onDayOpened] reports each day the user opens.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GourmetApp(
    modifier: Modifier = Modifier,
    restaurants: List<Restaurant> = Datasource.loadRestaurants(),
    openedDays: Set<Int> = emptySet(),
    onDayOpened: (Int) -> Unit = {},
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.displayLarge,
                    )
                },
                actions = {
                    OpenedCounter(
                        opened = restaurants.count { it.day in openedDays },
                        total = restaurants.size,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
            )
        },
    ) { innerPadding ->
        /*
         * The scaffold's insets go in as contentPadding rather than as a
         * Modifier.padding. With Modifier.padding the list would stop scrolling
         * above the navigation bar and leave a dead strip; as contentPadding the
         * content scrolls under it and simply starts and ends clear of it.
         */
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = innerPadding.calculateBottomPadding() + 8.dp,
            ),
        ) {
            items(restaurants, key = { it.day }) { restaurant ->
                RestaurantCard(
                    restaurant = restaurant,
                    opened = restaurant.day in openedDays,
                    onOpen = { onDayOpened(restaurant.day) },
                    modifier = Modifier.padding(vertical = 6.dp),
                )
            }
        }
    }
}

/** The "✓ 2/30" pill at the top right: how many of the days have been opened. */
@Composable
private fun OpenedCounter(
    opened: Int,
    total: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = modifier.padding(end = 8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = stringResource(R.string.days_opened),
                modifier = Modifier.size(14.dp),
            )
            Text(
                text = stringResource(R.string.opened_count, opened, total),
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 4.dp),
            )
        }
    }
}
