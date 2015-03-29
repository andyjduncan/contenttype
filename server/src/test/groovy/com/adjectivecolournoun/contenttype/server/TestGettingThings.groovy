package com.adjectivecolournoun.contenttype.server

import com.adjectivecolournoun.contenttype.server.controllers.ThingController
import com.adjectivecolournoun.contenttype.server.domain.Thing
import com.adjectivecolournoun.contenttype.server.domain.ThingOne
import com.adjectivecolournoun.contenttype.server.domain.ThingSummary
import com.adjectivecolournoun.contenttype.server.domain.ThingSummaryList
import com.adjectivecolournoun.contenttype.server.domain.ThingThree
import com.adjectivecolournoun.contenttype.server.domain.ThingTwo
import com.adjectivecolournoun.contenttype.server.repository.ThingRepository
import com.adjectivecolournoun.contenttype.server.service.ThingService
import spock.lang.Specification


class TestGettingThings extends Specification {

    ThingRepository repository = Mock()

    ThingService thingService = new ThingService(repository: repository)

    ThingController controller = new ThingController(thingService: thingService)

    void 'gets a list of all things'() {
        given:
        1 * repository.find() >> [thingOne(), thingTwo(), thingThree()]

        when:
        ThingSummaryList things = controller.list()
        println things

        then:
        things.things == [
                new ThingSummary(id: 'thing id', name: 'first thing'),
                new ThingSummary(id: 'thing id', name: 'second thing'),
                new ThingSummary(id: 'thing id', name: 'third thing')
        ]
    }

    void 'gets a version 1 thing'() {
        given:
        1 * repository.findOne('thing id') >> thingOne()

        when:
        Thing aThing = controller.versionOne('thing id')

        then:
        aThing.id == 'thing id'
        aThing.name == 'first thing'
    }

    void 'gets a version 2 thing'() {
        given:
        1 * repository.findOne('thing id') >> thingTwo()

        when:
        ThingTwo aThing = controller.versionTwo('thing id')

        then:
        aThing.id == 'thing id'
        aThing.name == 'second thing'
        aThing.type == 'a thing'
    }

    void 'gets a version 3 thing'() {
        given:
        1 * repository.findOne('thing id') >> thingThree()

        when:
        ThingThree aThing = controller.versionThree('thing id')

        then:
        aThing.id == 'thing id'
        aThing.name == 'third thing'
        aThing.type == 'a thing'
        aThing.count == 1
    }

    void 'coerces a version 2 thing to a version 1 thing on version 1 endpoint'() {
        given:
        1 * repository.findOne('thing id') >> thingTwo()

        when:
        Thing aThing = controller.versionOne('thing id')

        then:
        aThing.class == ThingOne
        aThing.name == 'second thing'
    }

    void 'coerces a version 3 thing to a version 1 thing on version 1 endpoint'() {
        given:
        1 * repository.findOne('thing id') >> thingThree()

        when:
        Thing aThing = controller.versionOne('thing id')

        then:
        aThing.class == ThingOne
        aThing.name == 'third thing'
    }

    void 'coerces a version 1 thing to a version 2 thing on version 2 endpoint'() {
        given:
        1 * repository.findOne('thing id') >> thingOne()

        when:
        ThingTwo aThing = controller.versionTwo('thing id')

        then:
        aThing.class == ThingTwo
        aThing.name == 'first thing'
        aThing.type == null
    }

    void 'coerces a version 3 thing to a version 2 thing on version 2 endpoint'() {
        given:
        1 * repository.findOne('thing id') >> thingThree()

        when:
        ThingTwo aThing = controller.versionTwo('thing id')

        then:
        aThing.class == ThingTwo
        aThing.name == 'third thing'
        aThing.type == 'a thing'
    }

    void 'coerces a version 1 thing to a version 3 thing on version 3 endpoint'() {
        given:
        1 * repository.findOne('thing id') >> thingOne()

        when:
        ThingThree aThing = controller.versionThree('thing id')

        then:
        aThing.class == ThingThree
        aThing.name == 'first thing'
        aThing.type == null
        aThing.count == null
    }

    void 'coerces a version 2 thing to a version 3 thing on version 3 endpoint'() {
        given:
        1 * repository.findOne('thing id') >> thingTwo()

        when:
        ThingThree aThing = controller.versionThree('thing id')

        then:
        aThing.class == ThingThree
        aThing.name == 'second thing'
        aThing.type == 'a thing'
        aThing.count == null
    }

    private ThingOne thingOne() {
        new ThingOne(id: 'thing id', name: 'first thing')
    }

    private ThingTwo thingTwo() {
        new ThingTwo(id: 'thing id', name: 'second thing', type: 'a thing')
    }

    private ThingThree thingThree() {
        new ThingThree(id: 'thing id', name: 'third thing', type: 'a thing', count: 1)
    }
}