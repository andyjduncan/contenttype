package com.adjectivecolournoun.contenttype.server.service

import com.adjectivecolournoun.contenttype.server.domain.Thing
import com.adjectivecolournoun.contenttype.server.domain.ThingOne
import com.adjectivecolournoun.contenttype.server.domain.ThingThree
import com.adjectivecolournoun.contenttype.server.domain.ThingTwo
import com.adjectivecolournoun.contenttype.server.repository.ThingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ThingService {

    @Autowired
    ThingRepository repository

    List<Thing> allThings() {
        repository.find()
    }

    public ThingOne loadAsVersionOne(String thingId) {
        def thing = repository.findOne(thingId)
        asVersionOne thing
    }

    public ThingTwo loadAsVersionTwo(String thingId) {
        def thing = repository.findOne(thingId)
        asVersionTwo thing
    }

    public ThingThree loadAsVersionThree(String thingId) {
        def thing = repository.findOne(thingId)
        asVersionThree thing
    }

    private static Thing asVersionOne(Thing thing) {
        new ThingOne(id: thing.id, name: thing.name)
    }

    private static ThingTwo asVersionTwo(Thing thing) {
        new ThingTwo(id: thing.id, name: thing.name, type: null)
    }

    private static ThingTwo asVersionTwo(ThingTwo thing) {
        new ThingTwo(id: thing.id, name: thing.name, type: thing.type)
    }

    private static ThingThree asVersionThree(Thing thing) {
        new ThingThree(id: thing.id, name: thing.name, type: null, count: null)
    }

    private static ThingThree asVersionThree(ThingTwo thing) {
        new ThingThree(id: thing.id, name: thing.name, type: thing.type, count: null)
    }

    private static ThingThree asVersionThree(ThingThree thing) {
        new ThingThree(id: thing.id, name: thing.name, type: thing.type, count: thing.count)
    }
}
