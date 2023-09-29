package so.dang.cool.openfga.preview

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dev.openfga.sdk.api.client.OpenFgaClient
import dev.openfga.sdk.api.configuration.ClientConfiguration
import dev.openfga.sdk.api.configuration.ClientCredentials
import dev.openfga.sdk.api.configuration.Credentials
import dev.openfga.sdk.api.model.WriteAuthorizationModelRequest

fun main() {
    // Setup
    val clientCreds =
        Credentials(
            ClientCredentials()
                .apiTokenIssuer(GlobalConstants.FGA_API_TOKEN_ISSUER)
                .apiAudience(GlobalConstants.FGA_API_AUDIENCE)
                .clientId(GlobalConstants.FGA_CLIENT_ID)
                .clientSecret(GlobalConstants.FGA_CLIENT_SECRET),
        )
    val config =
        ClientConfiguration()
            .apiUrl(GlobalConstants.FGA_SERVER_URL)
            .storeId(GlobalConstants.FGA_STORE_ID)
            .credentials(clientCreds)
    val fga = OpenFgaClient(config)

    // Create Auth Model
    val mapper = jacksonObjectMapper()
    val authModelJson = exampleAuthModelJson()
    val request = mapper.readValue<WriteAuthorizationModelRequest>(authModelJson)

    // Write the Auth Model
    val response = fga.writeAuthorizationModel(request).get()

    // Show
    println("The Authorization Model ID is: ${response.authorizationModelId}")
}

fun exampleAuthModelJson() =
    """
    {
      "schema_version": "1.1",
      "type_definitions": [
        {"type": "user"},
        {
          "type":"document",
          "relations": {
            "reader": {
              "this": {}
            },
            "writer": {
              "this": {}
            },
            "owner": {
              "this": {}
            }
          },
          "metadata": {
            "relations": {
              "reader": {
                "directly_related_user_types": [
                  { "type": "user" }
                ]
              },
              "writer": {
                "directly_related_user_types": [
                  { "type": "user"}
                ]
              },
              "owner": {
                "directly_related_user_types": [
                  { "type": "user"}
                ]
              }
            }
          }
        }
      ]
    }
    """.trimIndent()
