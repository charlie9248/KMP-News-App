package newsApi

import data.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.statement.HttpResponse
import util.Resource

interface NewsRepositoryApi {
    suspend fun getBreakingNews(
        countryCode: String = "us",
        pageNumber: Int = 1,
        apiKey: String
    ) :NewsResponse


    suspend fun searchBreakingNews(
        query: String ,
        pageNumber: Int = 1,
        apiKey: String
    ) : NewsResponse

}