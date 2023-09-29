package so.dang.cool.openfga.preview

import static so.dang.cool.openfga.preview.GlobalConstants.*

import dev.openfga.sdk.api.client.ClientTupleKey
import dev.openfga.sdk.api.client.ClientWriteRequest
import dev.openfga.sdk.api.client.OpenFgaClient
import dev.openfga.sdk.api.configuration.ClientConfiguration
import dev.openfga.sdk.api.configuration.ClientCredentials
import dev.openfga.sdk.api.configuration.Credentials

static void main(String[] args) {
	// Setup
	def config = new ClientConfiguration()
			.apiUrl(FGA_SERVER_URL)
			.storeId(FGA_STORE_ID)
			.credentials(new Credentials(
			new ClientCredentials()
			.apiTokenIssuer(FGA_API_TOKEN_ISSUER)
			.apiAudience(FGA_API_AUDIENCE)
			.clientId(FGA_CLIENT_ID)
			.clientSecret(FGA_CLIENT_SECRET)
			))
	def fga = new OpenFgaClient(config)

	// Write a relationship
	def request = new ClientWriteRequest()
			.writes([
				new ClientTupleKey()
				.user('user:anne')
				.relation('reader')
				._object('document:Z')
			])
	fga.write(request)
}