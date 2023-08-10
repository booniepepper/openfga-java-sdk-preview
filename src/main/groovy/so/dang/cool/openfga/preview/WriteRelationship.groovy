package so.dang.cool.openfga.preview

import com.fasterxml.jackson.databind.json.JsonMapper
import dev.openfga.api.OpenFgaApi
import dev.openfga.api.invoker.ApiClient
import dev.openfga.api.model.TupleKey
import dev.openfga.api.model.TupleKeys
import dev.openfga.api.model.WriteRequest

import java.net.http.HttpClient

static void main(String[] args) {
  // Setup
  def config = ApiClient.Configuration.of 'http://localhost:8080'
  def apiClient = new ApiClient(HttpClient.newBuilder(), new JsonMapper(), config)
  def fga = new OpenFgaApi(apiClient)

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