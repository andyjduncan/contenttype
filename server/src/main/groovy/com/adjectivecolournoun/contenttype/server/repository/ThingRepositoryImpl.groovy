package com.adjectivecolournoun.contenttype.server.repository

import com.adjectivecolournoun.contenttype.server.domain.Thing
import com.adjectivecolournoun.contenttype.server.domain.ThingOne
import com.adjectivecolournoun.contenttype.server.domain.ThingThree
import com.adjectivecolournoun.contenttype.server.domain.ThingTwo
import org.springframework.stereotype.Repository

import static java.util.UUID.randomUUID

@Repository
class ThingRepositoryImpl implements ThingRepository {

    private final Map<String, Thing> things = [:]

    @Override
    Thing findOne(String id) {
        cloneThing things[id]
    }

    @Override
    Thing save(Thing thing) {
        def newThing = cloneThing(thing)

        newThing.id = newThing.id ?: randomUUID().toString()
        things[newThing.id] = newThing

        newThing
    }

    @Override
    Iterable<Thing> find() {
        things.values().collect {
            cloneThing it
        }
    }

    private static Thing cloneThing(ThingOne thingOne) {
        new ThingOne(id: thingOne.id, name: thingOne.name)
    }

    private static Thing cloneThing(ThingTwo thingTwo) {
        new ThingTwo(id: thingTwo.id, name: thingTwo.name, type: thingTwo.type)
    }

    private static Thing cloneThing(ThingThree thingThree) {
        new ThingThree(id: thingThree.id, name: thingThree.name, type: thingThree.type, count: thingThree.count)
    }
}
