package com.example.app.flightproviders.controller;

import com.example.app.flightproviders.requests.SearchRequestA;
import com.example.app.flightproviders.service.SearchResult;
import com.example.app.flightproviders.service.SearchServiceA;
import com.example.app.flightproviders.requests.SearchRequestB;
import com.example.app.flightproviders.service.SearchServiceB;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProvidersEndPoints {
    private static final String NAMESPACE_URI = "http://petour.com/flights";
    private final SearchServiceA searchServiceA;
    private final SearchServiceB searchServiceB;

    public ProvidersEndPoints(
            SearchServiceA searchServiceA,
            SearchServiceB searchServiceB
    ) {

        this.searchServiceA = searchServiceA;
        this.searchServiceB = searchServiceB;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProviderARequest")
    @ResponsePayload
    public SearchResult getFlightsAvailable(@RequestPayload SearchRequestA searchRequest) {
        return searchServiceA.availabilitySearch(searchRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ProviderBRequest")
    @ResponsePayload
    public SearchResult getFlightsAvailable(@RequestPayload SearchRequestB searchRequest) {
        return searchServiceB.availabilitySearch(searchRequest);
    }

}
