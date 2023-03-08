package com.example.rikotodanactrl.core

object HttpInfo {
    private var BaseUrl = "http://REST-API-IP:8000"
    var InvEndpoint = "$BaseUrl/inventory"
    var ItemEndpoint = "$BaseUrl/item/"
    var ExpiryEndpoint = "$BaseUrl/foodalerts/expiry"
    var RecallsEndpoints = "$BaseUrl/foodalerts/recall"
    fun setBase(burl: String) {
        BaseUrl = burl
        InvEndpoint = "$BaseUrl/inventory"
        ItemEndpoint = "$BaseUrl/item/"
        ExpiryEndpoint = "$BaseUrl/foodalerts/expiry"
        RecallsEndpoints = "$BaseUrl/foodalerts/recall"
    }
}