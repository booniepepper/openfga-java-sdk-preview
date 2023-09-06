package so.dang.cool.openfga.preview

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.openfga.sdk.api.OpenFgaApi
import dev.openfga.sdk.api.client.ApiClient
import dev.openfga.sdk.api.configuration.Configuration
import dev.openfga.sdk.api.model.WriteAuthorizationModelRequest
import java.net.http.HttpClient

fun main() {
    // Setup
    val mapper = jacksonObjectMapper()
    val config = Configuration("http://localhost:8080")
    val apiClient = ApiClient(HttpClient.newBuilder(), mapper)
    val fga = OpenFgaApi(apiClient, config)

    // Create Auth Model
    val storeId = ""
    val modelJson = "{\"schema_version\":\"1.1\",\"type_definitions\":[{\"type\":\"user\"},{\"type\":\"document\",\"relations\":{\"reader\":{\"this\":{}},\"writer\":{\"this\":{}},\"owner\":{\"this\":{}}},\"metadata\":{\"relations\":{\"reader\":{\"directly_related_user_types\":[{\"type\":\"user\"}]},\"writer\":{\"directly_related_user_types\":[{\"type\":\"user\"}]},\"owner\":{\"directly_related_user_types\":[{\"type\":\"user\"}]}}}}]}"
    val request = mapper.readValue<WriteAuthorizationModelRequest>(modelJson)

    val response = fga.writeAuthorizationModel(storeId, request).get()

    // Show
    println("The Authorization Model ID is: ${response.authorizationModelId}")
}