package com.adjectivecolournoun.contenttype.server.repository

import com.adjectivecolournoun.contenttype.server.domain.Thing

interface ThingRepository {

    Thing findOne(String id)

    Thing save(Thing thing)

    Iterable<Thing> find()
}