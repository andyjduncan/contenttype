package com.adjectivecolournoun.contenttype.server.controllers

import com.adjectivecolournoun.contenttype.server.domain.*
import com.adjectivecolournoun.contenttype.server.service.ThingService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = '/thing', method = RequestMethod.GET)
@Slf4j
class ThingController {

    @Autowired
    ThingService thingService

    @RequestMapping
    ThingSummaryList list() {
        def summaryList = thingService.allThings().collect {
            new ThingSummary(it.id, it.name)
        }

        new ThingSummaryList(summaryList)
    }

    @RequestMapping(value = '/{thingId}', produces = ['application/vnd.acn.thing+json', 'application/vnd.acn.thing-v1+json'])
    ThingOne versionOne(@PathVariable String thingId) {
        log.info 'Producing version 1 thing'
        thingService.loadAsVersionOne thingId
    }

    @RequestMapping(value = '/{thingId}', produces = 'application/vnd.acn.thing-v2+json')
    ThingTwo versionTwo(@PathVariable String thingId) {
        log.info 'Producing version 2 thing'
        thingService.loadAsVersionTwo thingId
    }

//    @RequestMapping(value = '/{thingId}', produces = 'application/vnd.acn.thing-v3+json')
    ThingThree versionThree(@PathVariable String thingId) {
        log.info 'Producing version 3 thing'
        thingService.loadAsVersionThree thingId
    }
}
