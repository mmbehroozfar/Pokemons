package com.mmb.data.mapper

import com.mmb.data.local.model.ItemEntity
import com.mmb.data.remote.model.ItemResponse
import com.mmb.domain.model.HeldItemDetail
import javax.inject.Inject

class HeldItemDetailMapper @Inject constructor() {

    operator fun invoke(response: ItemResponse): ItemEntity {
        return ItemEntity(
            name = response.name,
            url = response.url,
        )
    }

    operator fun invoke(entity: ItemEntity): HeldItemDetail {
        return HeldItemDetail(
            name = entity.name,
            url = entity.url,
        )
    }
}