package home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberImagePainter
import data.Article
import detail.DetailScreen

class HomeScreen : Screen {

    @Composable
    override fun Content() {

        val viewModel = getScreenModel<HomeViewModel>()

        val articles = viewModel.breakingNewsList.collectAsState()

        DisplaySection(
            modifier = Modifier.fillMaxSize(),
            articles = articles.value ,
            onItemClicked = {}
        )
    }

    @Composable
    fun DisplaySection(
        modifier: Modifier = Modifier,
        articles: List<Article>,
        onItemClicked: (Article) -> Unit
    ) {
        Surface(modifier = modifier.fillMaxSize(), color = Color.White,) {
            Column(modifier = Modifier.padding(start = 24.dp, top = 20.dp, end = 24.dp)) {

                val listState = rememberLazyListState()
                DisplayList(articles, onItemClicked, listState = listState)
            }
        }
    }

    @Composable
    private fun DisplayList(
        articles: List<Article>,
        onItemClicked: (Article) -> Unit ,
        modifier: Modifier = Modifier,
        listState: LazyListState = rememberLazyListState()
    ) {
        LazyColumn(
            modifier = modifier,
            contentPadding = WindowInsets.navigationBars.asPaddingValues(),
            state = listState
        ) {
            items(articles) { article ->
                Column(Modifier.fillParentMaxWidth()) {
                    DisplayItem(
                        modifier = Modifier.fillParentMaxWidth(),
                        item = article,
                        onItemClicked = onItemClicked
                    )
                    Divider(color = Color.Gray)
                }
            }
        }
    }

    @Composable
    private fun DisplayItem(
        modifier: Modifier = Modifier,
        item: Article,
        onItemClicked: (Article) -> Unit
    ) {
        val navigator = LocalNavigator.currentOrThrow
        Row(
            modifier = modifier
                .clickable {
                    navigator.push(DetailScreen(item))
                }
                .padding(top = 12.dp, bottom = 12.dp)
        ) {
            ExploreImageContainer {
                Box {
                    val backupImageUrl = "https://i0.wp.com/learn.onemonth.com/wp-content/uploads/2017/08/1-10.png?fit=845%2C503&ssl=1"
                    val imageUrl = item.urlToImage ?: backupImageUrl
                    val painter = rememberImagePainter(imageUrl)

                    Image(painter , modifier = Modifier.height(130.dp).shadow(
                        elevation = 1.dp,
                        shape = RoundedCornerShape(20.dp)) , contentDescription = item.title)

                }
            }
            Spacer(Modifier.width(24.dp))
            Column {
                Text(
                    text = item.title.toString(),
                    style = MaterialTheme.typography.h6
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = item.description.toString(),
                    style = MaterialTheme.typography.caption.copy(color = Color.Black)
                )
            }
        }
    }

    @Composable
    private fun ExploreImageContainer(content: @Composable () -> Unit) {
        Surface(Modifier.size(width = 120.dp, height = 120.dp), RoundedCornerShape(4.dp)) {
            content()
        }
    }
}