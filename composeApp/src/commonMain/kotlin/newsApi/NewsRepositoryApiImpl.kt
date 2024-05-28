package newsApi

import data.NewsResponse
import httpClient.httpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter


const val BASE_URL = "https://newsapi.org/v2/"

class NewsRepositoryApiImpl : NewsRepositoryApi {

    override suspend fun getBreakingNews(
        countryCode: String,
        pageNumber: Int,
        apiKey: String
    ): NewsResponse {

        val response =  httpClient  .get("https://newsapi.org/v2/top-headlines"){
            parameter("country", countryCode)
            parameter("page", pageNumber)
            parameter("apiKey", apiKey)
        }

        return  response.body()
    }

    override suspend fun searchBreakingNews(
        query: String,
        pageNumber: Int,
        apiKey: String
    ): NewsResponse {
        val response =   httpClient.get("https://newsapi.org/v2/everything") {
            parameter("q", query)
            parameter("page", pageNumber)
            parameter("apiKey", apiKey)
        }

        return response.body()
    }
}