package home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import data.Article
import data.NewsResponse
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import newsApi.NewsRepositoryApi

const val API_KEY  = "5ba8ee8fa52c4d2b9fd6eabbc8f3ace1"

class HomeViewModel(private val newsRepositoryApi: NewsRepositoryApi)  : ScreenModel{



    private  var _breakingNewsList  = MutableStateFlow<List<Article>>(listOf())
    val breakingNewsList  =  _breakingNewsList.asStateFlow()


    private  var _searchBreakingNews  = MutableStateFlow<List<Article>>(listOf())
    val searchBreakingNews  =  _searchBreakingNews.asStateFlow()


    init {
        getBreakingNews()
    }
     fun getBreakingNews(
        countryCode: String = "us",
        pageNumber: Int = 1
    ) {

         screenModelScope.launch(Dispatchers.IO) {
             try {
                 val response  =  newsRepositoryApi.getBreakingNews(
                     countryCode = countryCode,
                     pageNumber = pageNumber,
                     apiKey = API_KEY)
                 _breakingNewsList.value = response.articles

             }catch (e : Exception){
                 println(e.message)
             }
         }
    }


     fun searchBreakingNews(
         query: String ,
        pageNumber: Int = 1,
    ) {
         screenModelScope.launch(Dispatchers.Main) {
             try {
                 val response  =  newsRepositoryApi.searchBreakingNews(
                     query = query,
                     pageNumber = pageNumber,
                     apiKey = API_KEY)
                 _searchBreakingNews.value = response.articles

             }catch (e : Exception){
                 println(e.message)
             }
         }
    }
}