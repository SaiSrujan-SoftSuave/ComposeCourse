package com.example.composecourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecourse.Constants.alignYourBodyData
import com.example.composecourse.Constants.favoriteCollectionsData
import com.example.composecourse.ui.theme.ComposeCourseTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSize = calculateWindowSizeClass(activity = this)
            ComposeCourseTheme {
                MySootheApp(windowSize)
            }
        }
    }
}

    // Step: Search bar - Modifiers
    @Composable
    fun SearchBar(
        modifier: Modifier = Modifier
    ) {
        var enteredValue by remember {
            mutableStateOf("")
        }
        TextField(value = enteredValue,
            onValueChange = { enteredValue = it },
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search, contentDescription = "Search icon"
                )
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            placeholder = {
                Text(text = stringResource(id = R.string.placeholder_search))
            }

        )
    }

    // Step: Align your body - Alignment
    @Composable
    fun AlignYourBodyElement(
        modifier: Modifier = Modifier,
        image: Int = R.drawable.ab1_inversions,
        title: String = stringResource(id = R.string.ab1_inversions)
    ) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .clip(shape = CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp)
            )
        }

    }

    // Step: Favorite collection card - Material Surface
    @Composable
    fun FavoriteCollectionCard(
        modifier: Modifier = Modifier,
        painterRes: Int = R.drawable.fc2_nature_meditations,
        stringRes: Int = R.string.fc2_nature_meditations
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = modifier.width(255.dp),
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = painterRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                )
                Text(
                    text = stringResource(id = stringRes),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }

    // Step: Align your body row - Arrangements
    @Composable
    fun AlignYourBodyRow(
        modifier: Modifier = Modifier
    ) {
        LazyRow(
            modifier = modifier,
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(alignYourBodyData) { item ->
                AlignYourBodyElement(
                    image = item.drawable, title = stringResource(id = item.text)
                )
            }
        }
    }

    // Step: Favorite collections grid - LazyGrid
    @Composable
    fun FavoriteCollectionsGrid(
        modifier: Modifier = Modifier
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = modifier.height(168.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(favoriteCollectionsData) { item ->
                FavoriteCollectionCard(painterRes = item.drawable, stringRes = item.text)
            }
        }
    }

    // Step: Home section - Slot APIs
    @Composable
    fun HomeSection(
        modifier: Modifier = Modifier, @StringRes title: Int, content: @Composable () -> Unit
    ) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
            )
            content()
        }
    }

    // Step: Home screen - Scrolling
    @Composable
    fun HomeScreen(modifier: Modifier = Modifier) {
        Column(modifier = modifier
            .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState())) {
            SearchBar(modifier = Modifier.padding(horizontal = 16.dp))
            HomeSection(title = R.string.align_your_body) {
                AlignYourBodyRow()
            }
            HomeSection(title = R.string.favorite_collections) {
                FavoriteCollectionsGrid()
            }
        }
    }

    // Step: Bottom navigation - Material
    @Composable
    private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
        NavigationBar(
            modifier = modifier,
            contentColor = MaterialTheme.colorScheme.surfaceVariant
        ) {
            NavigationBarItem(selected = true, onClick = { /*TODO*/ }, icon = {
                Icon(
                    imageVector = Icons.Filled.Home, contentDescription = null
                )
            }, label = {
                Text(text = stringResource(id = R.string.bottom_navigation_home))
            })

            NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
                Icon(
                    imageVector = Icons.Filled.AccountCircle, contentDescription = null
                )
            }, label = {
                Text(text = stringResource(id = R.string.bottom_navigation_profile))
            })
        }
    }

    // Step: MySoothe App - Scaffold
    @Composable
    fun MySootheAppPortrait() {
        // Implement composable here
        ComposeCourseTheme {
            Scaffold(bottomBar = {
                SootheBottomNavigation()
            }) { paddingValues ->
                HomeScreen(Modifier.padding(paddingValues))
            }
        }
    }

    // Step: Bottom navigation - Material
    @Composable
    private fun SootheNavigationRail(modifier: Modifier = Modifier) {
        NavigationRail(
            containerColor = MaterialTheme.colorScheme.background,
            modifier = modifier.padding(start = 8.dp, end = 10.dp)
        ) {
            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                NavigationRailItem(
                    selected = true,
                    onClick = { /*TODO*/ },
                    icon = { Icon(imageVector =Icons.Filled.Home, contentDescription = null) },
                    label = {
                        Text(text = stringResource(id = R.string.bottom_navigation_home))
                    })

                NavigationRailItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = { Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = null
                    ) },
                    label = {
                        Text(text = stringResource(id = R.string.bottom_navigation_profile))
                    })
            }
        }
    }

    // Step: Landscape Mode
    @Composable
    fun MySootheAppLandscape() {
        ComposeCourseTheme {

            Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
                Row {
                    SootheNavigationRail()
                    HomeScreen()
                }
            }
        }
    }

    // Step: MySoothe App
    @Composable
    fun MySootheApp(windowSize: WindowSizeClass) {
        // Implement composable here
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                MySootheAppPortrait()
            }

            WindowWidthSizeClass.Expanded -> {
                MySootheAppLandscape()
            }

        }
    }

        @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
        @Composable
        fun SearchBarPreview() {
            ComposeCourseTheme { SearchBar(Modifier.padding(8.dp)) }
        }

        @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
        @Composable
        fun AlignYourBodyElementPreview() {
            ComposeCourseTheme {
                AlignYourBodyElement(
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
        @Composable
        fun FavoriteCollectionCardPreview() {
            ComposeCourseTheme {
                FavoriteCollectionCard(
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
        @Composable
        fun FavoriteCollectionsGridPreview() {
            ComposeCourseTheme { FavoriteCollectionsGrid() }
        }

        @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
        @Composable
        fun AlignYourBodyRowPreview() {
            ComposeCourseTheme { AlignYourBodyRow() }
        }

        @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
        @Composable
        fun HomeSectionPreview() {
            ComposeCourseTheme {
                HomeSection(title = R.string.align_your_body) {
                    AlignYourBodyRow()
                }
            }
        }

        @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
        @Composable
        fun ScreenContentPreview() {
            ComposeCourseTheme { HomeScreen() }
        }

        @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
        @Composable
        fun BottomNavigationPreview() {
            ComposeCourseTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
        }

        @Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
        @Composable
        fun NavigationRailPreview() {
            ComposeCourseTheme { SootheNavigationRail() }
        }

        @Preview(widthDp = 360, heightDp = 640)
        @Composable
        fun MySoothePortraitPreview() {
            MySootheAppPortrait()
        }

        @Preview(widthDp = 640, heightDp = 360)
        @Composable
        fun MySootheLandscapePreview() {
            MySootheAppLandscape()
        }


