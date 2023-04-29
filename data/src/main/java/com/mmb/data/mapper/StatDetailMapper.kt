package com.mmb.data.mapper

import com.mmb.data.local.model.StatDetailEntity
import com.mmb.data.remote.model.StatDetailResponse
import com.mmb.domain.model.StatDetail
import javax.inject.Inject

class StatDetailMapper @Inject constructor() {

    operator fun invoke(response: StatDetailResponse): StatDetailEntity {
        return StatDetailEntity(
            name = response.name,
            url = response.url,
        )
    }

    operator fun invoke(entity: StatDetailEntity): StatDetail {
        return StatDetail(
            name = entity.name,
            url = entity.url,
        )
    }
}