import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import detail.DetailViewModel
import home.HomeScreen
import home.HomeViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kpmnewsapp.composeapp.generated.resources.Res
import kpmnewsapp.composeapp.generated.resources.compose_multiplatform
import newsApi.NewsRepositoryApi
import newsApi.NewsRepositoryApiImpl
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Composable
@Preview
fun App() {

    // this is for initialization of koin dependency Injection
    initializeKoin()
    MaterialTheme {
            Navigator(HomeScreen()){
                SlideTransition(it)
            }
    }
}

val appModule  = module {
    single<NewsRepositoryApi> { NewsRepositoryApiImpl() }
    factory { HomeViewModel(get()) }
    factory { DetailViewModel(get()) }
}

fun initializeKoin(){
    startKoin{
        modules(appModule)
    }
}

