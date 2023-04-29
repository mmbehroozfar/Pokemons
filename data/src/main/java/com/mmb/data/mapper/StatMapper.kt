package com.mmb.data.mapper

import com.mmb.data.local.model.StatEntity
import com.mmb.data.remote.model.StatResponse
import com.mmb.domain.model.Stat
import javax.inject.Inject

class StatMapper @Inject constructor(
    private val statDetailMapper: StatDetailMapper,
) {

    operator fun invoke(response: StatResponse): StatEntity {
        return StatEntity(
            baseStat = response.baseStat,
            effort = response.effort,
            stat = statDetailMapper(response.stat),
        )
    }

    operator fun invoke(entity: StatEntity): Stat {
        return Stat(
            baseStat = entity.baseStat,
            effort = entity.effort,
            stat = statDetailMapper(entity.stat),
        )
    }
}