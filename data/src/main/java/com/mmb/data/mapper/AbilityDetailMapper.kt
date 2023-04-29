package com.mmb.data.mapper

import com.mmb.data.local.model.AbilityDetailEntity
import com.mmb.data.remote.model.AbilityDetailResponse
import com.mmb.domain.model.AbilityDetail
import javax.inject.Inject

class AbilityDetailMapper @Inject constructor() {

    operator fun invoke(response: AbilityDetailResponse): AbilityDetailEntity {
        return AbilityDetailEntity(
            name = response.name,
            url = response.url,
        )
    }

    operator fun invoke(entity: AbilityDetailEntity): AbilityDetail {
        return AbilityDetail(
            name = entity.name,
            url = entity.url,
        )
    }
}