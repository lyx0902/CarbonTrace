package com.example.carbontrace.Home

import androidx.annotation.DrawableRes
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carbontrace.R
import com.example.carbontrace.ScreenType
import com.example.carbontrace.article.AQScreen
import com.example.carbontrace.article.Paragraph
import com.example.carbontrace.article.Post
import com.example.carbontrace.article.PostContent
import com.example.carbontrace.article.PostType
import com.example.carbontrace.article.defaultSpacerSize
import com.example.carbontrace.article.post1
import com.example.carbontrace.article.post2
import com.example.carbontrace.article.postContentItems
import com.example.carbontrace.screenType
import com.example.carbontrace.switchScreenType
import com.example.carbontrace.targetPost

@Composable
fun PostCardSimple(
    post: Post,
) {
    HorizontalDivider(thickness = 2.dp)
    Row(
        modifier = Modifier
            .clickable(onClick = { navigateToArticle(post) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(post.imageId),
            contentDescription = null,
            modifier = Modifier.padding(vertical = 16.dp)
                .size(40.dp, 40.dp)
                .clip(MaterialTheme.shapes.small),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(14.dp)
        ) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            Row{
                Text(
                    text = "${post.metadata.date} 预计阅读时间${post.metadata.readTimeMinutes}分钟",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        when(post.type){
            PostType.ACTIVITY -> { Icon(painterResource(R.drawable.tour), contentDescription = "") }
            PostType.ARTICLE -> { Icon(painterResource(R.drawable.article), contentDescription = "") }
            PostType.QUIZ -> { Icon(painterResource(R.drawable.quiz), contentDescription = "") }
        }
    }
}

fun navigateToArticle(post: Post){
    screenType = ScreenType.AQDETAILS
    targetPost = post
}

@Composable
fun HomeScreen(
    homeListLazyListState: LazyListState = rememberLazyListState(),
    modifier: Modifier = Modifier,
) {
    //测试文章列表
    val postList = listOf(post1, post2)
    Scaffold(
        bottomBar = { BottomNavigation() }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(horizontal = defaultSpacerSize),
            state = homeListLazyListState,
        ) {
            item {
                Spacer(Modifier.height(defaultSpacerSize/2))
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp),
                    text = "活动|文章|问题集",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.height(defaultSpacerSize))
            }
            items(postList) { PostCardSimple(it) }
        }
    }
}

//底部导航栏，在本页面中只让Profile的onclick产生页面切换效果，让Home的被选中属性为真
@Composable
private fun BottomNavigation(modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Home"
                )
            },
            selected = true,
            onClick = { }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Profile"
                )
            },
            selected = false,
            onClick = { switchScreenType() }
        )
    }
}