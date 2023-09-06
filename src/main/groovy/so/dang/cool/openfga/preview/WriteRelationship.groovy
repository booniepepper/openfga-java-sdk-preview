package so.dang.cool.openfga.preview

import com.fasterxml.jackson.databind.json.JsonMapper
import dev.openfga.sdk.api.OpenFgaApi
import dev.openfga.sdk.api.client.ApiClient
import dev.openfga.sdk.api.configuration.Configuration
import dev.openfga.sdk.api.model.TupleKey
import dev.openfga.sdk.api.model.TupleKeys
import dev.openfga.sdk.api.model.WriteRequest

import java.net.http.HttpClient

static void main(String[] args) {
  // Setup
  def config = new Configuration('http://localhost:8080')
  def apiClient = new ApiClient(HttpClient.newBuilder(), new JsonMapper())
  def fga = new OpenFgaApi(apiClient, config)

  // Write a relationship
  def storeId = ''
  def request = new WriteRequest()
          .authorizationModelId('')
          .writes(new TupleKeys().tupleKeys([
                  new TupleKey()
                          .user('user:anne')
                          .relation('reader')
                          ._object('document:Z')
          ]))
  fga.write(storeId, request)
}