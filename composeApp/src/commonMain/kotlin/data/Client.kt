package data

import io.ktor.client.call.body
import io.ktor.client.request.get
import model.PhotoItem
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json


object Client {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getPhotos(): List<PhotoItem> {

        return httpClient
            .get("https://jsonplaceholder.typicode.com/photos")
            .body<List<PhotoItem>>()
    }
}