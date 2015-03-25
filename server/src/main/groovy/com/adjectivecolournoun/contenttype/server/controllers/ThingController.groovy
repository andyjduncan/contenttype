package com.adjectivecolournoun.contenttype.server.controllers

import com.adjectivecolournoun.contenttype.server.domain.Thing
import com.adjectivecolournoun.contenttype.server.domain.ThingThree
import com.adjectivecolournoun.contenttype.server.domain.ThingTwo
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = '/thing', method = RequestMethod.GET)
@Slf4j
class ThingController {

    @RequestMapping(produces = ['application/vnd.acn.thing+json', 'application/vnd.acn.thing-v1+json'])
    Thing versionOne() {
        log.info 'Producing version 1 thing'
        new Thing(name: 'first thing')
    }

    @RequestMapping(produces = 'application/vnd.acn.thing-v2+json')
    ThingTwo versionTwo() {
        log.info 'Producing version 2 thing'
        new ThingTwo(name: 'second thing', type: 'a thing')
    }

//    @RequestMapping(produces = 'application/vnd.acn.thing-v3+json')
//    ThingThree versionThree() {
//        log.info 'Producing version 3 thing'
//        new ThingThree(name: 'third thing', type: 'a thing', count: 123)
//    }
}
