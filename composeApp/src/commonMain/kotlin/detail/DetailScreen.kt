package detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.seiko.imageloader.rememberImagePainter
import data.Article

class DetailScreen(private val article: Article) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.White,
                    title = { Text(text = "News  Details") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator.pop()
                        }){
                            Icon(imageVector = Icons.Filled.ArrowBack , contentDescription = "null")
                        }
                    }
                ) },

            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        //navigator.push(TaskScreen())
                    },
                    shape = RoundedCornerShape(12.dp)
                ){
                    Icon( imageVector = Icons.Default.Edit , contentDescription = "Edit Icons")
                }
            }

        ) { padding  ->

            Column(modifier = Modifier.fillMaxSize().padding(top = 24.dp).padding(top = padding.calculateTopPadding())) {
                ArticleImageDisplay(
                    article= article,
                    modifier = Modifier.weight(1f),)

                Spacer(Modifier.height(24.dp))

                ArticleDetailSection(
                    article= article,
                    modifier = Modifier.weight(1f),)
            }
        }
    }
}

    @Composable
    fun ArticleImageDisplay(
        article: Article,
        modifier: Modifier,
    ){
        Box(
            contentAlignment = Alignment.Center,
            modifier =  modifier.fillMaxSize()) {
            val backupImageUrl = "https://i0.wp.com/learn.onemonth.com/wp-content/uploads/2017/08/1-10.png?fit=845%2C503&ssl=1"
            val imageUrl = article.urlToImage ?: backupImageUrl
            val painter = rememberImagePainter(imageUrl)

            Image(painter , modifier = Modifier.fillMaxHeight().shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(2.dp)) , contentDescription = article.title)

        }

    }


@Composable
fun ArticleDetailSection(
    article: Article,
    modifier: Modifier,
){
    Column(modifier = modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text  = article.title.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text  = article.description.toString(),
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text  = if(article.author == null) "UnKnown " else article.author.toString() ,
            fontWeight = FontWeight.Medium
        )
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text  = article.publishedAt.toString() ,
            fontWeight = FontWeight.Medium
        )



    }
}