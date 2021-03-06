package com.adjectivecolournoun.contenttype.client

import org.springframework.http.HttpRequest
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.client.support.HttpRequestWrapper
import org.springframework.web.client.RestTemplate

def thingEndpoint = 'http://localhost:8080/thing'

def template = new RestTemplate()

def versionZero = MediaType.parseMediaType('application/vnd.acn.thing+json;charset=UTF-8')
def versionOne = MediaType.parseMediaType('application/vnd.acn.thing-v1+json;charset=UTF-8')
def versionTwo = MediaType.parseMediaType('application/vnd.acn.thing-v2+json;charset=UTF-8')
def versionThree = MediaType.parseMediaType('application/vnd.acn.thing-v3+json;charset=UTF-8')

def thingIds = template.getForEntity(thingEndpoint, Map).body.things*.id

[
        'application/vnd.acn.thing+json',
        'application/vnd.acn.thing-v3+json;q=0.7,application/vnd.acn.thing-v2+json;q=0.2,application/vnd.acn.thing-v1+json;q=0.1'
].each { contentType ->
    def contentTypeInterceptor = new AcceptHeaderHttpRequestInterceptor(contentType)
    template.interceptors = [contentTypeInterceptor]

    thingIds.each { id ->
        def response = template.getForEntity("$thingEndpoint/$id", Map)
        def thing = new Thing(response.body)
        def type = response.headers.getContentType()
        println "Got content type $type"
        switch (type) {
            case versionZero:
            case versionOne: handleVersionOne(thing); break
            case versionTwo: handleVersionTwo(thing); break
            case versionThree: handleVersionThree(thing); break
        }
    }
}

void handleVersionOne(Thing thing) {
    println 'Got a version one thing:'
    println "Name: $thing.name"
}

void handleVersionTwo(Thing thing) {
    println 'Got a version two thing:'
    println "Name: $thing.name"
    println "Type: $thing.type"
}

void handleVersionThree(Thing thing) {
    println 'Got a version three thing:'
    println "Name: $thing.name"
    println "Type: $thing.type"
    println "Count: $thing.count"
}

class AcceptHeaderHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private final List<MediaType> headerValue

    public AcceptHeaderHttpRequestInterceptor(String headerValue) {
        this.headerValue = MediaType.parseMediaTypes(headerValue)
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request)
        requestWrapper.headers.accept = headerValue

        execution.execute requestWrapper, body
    }
}