package httpClient

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

val httpClient = HttpClient {
    install(ContentNegotiation){
        json(Json{
            prettyPrint = true
            ignoreUnknownKeys =  true
        })
    }

}
