package so.dang.cool.openfga.preview

import dev.openfga.sdk.api.client.{ClientReadRequest, OpenFgaClient}
import dev.openfga.sdk.api.configuration.{
  ClientConfiguration,
  ClientCredentials,
  Credentials
}
import so.dang.cool.openfga.preview.GlobalConstants.*

def main(args: Array[String]): Unit = {
  val config = new ClientConfiguration()
    .apiUrl(FGA_SERVER_URL)
    .storeId(FGA_STORE_ID)
    .credentials(
      new Credentials(
        new ClientCredentials()
          .apiTokenIssuer(FGA_API_TOKEN_ISSUER)
          .apiAudience(FGA_API_AUDIENCE)
          .clientId(FGA_CLIENT_ID)
          .clientSecret(FGA_CLIENT_SECRET)
      )
    )
  val fga = new OpenFgaClient(config)

  // Create a Read Relationships query
  val request = new ClientReadRequest().user("user:anne")._object("document:")

  // Read Relationships
  val response = fga.read(request).get()

  // Print results
  println(s"Anne's documents: ${response.getTuples}")
}
