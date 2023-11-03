(ns so.dang.cool.main
  (:gen-class)
  (:import (so.dang.cool.openfga.preview GlobalConstants)
           (dev.openfga.sdk.api.client ClientTupleKey ClientWriteRequest OpenFgaClient)
           (dev.openfga.sdk.api.configuration ClientConfiguration ClientCredentials Credentials)))

(defn -main
    "Check"
    [& args]
    (println (.FGA_SERVER_URL GlobalContants)))
