package com.mmb.data.mapper

import com.mmb.data.local.model.HeldItemEntity
import com.mmb.data.remote.model.HeldItemResponse
import com.mmb.domain.model.HeldItem
import javax.inject.Inject

class HeldItemMapper @Inject constructor(
    private val heldItemDetailMapper: HeldItemDetailMapper,
) {

    operator fun invoke(response: HeldItemResponse): HeldItemEntity {
        return HeldItemEntity(
            item = heldItemDetailMapper(response.item)
        )
    }

    operator fun invoke(entity: HeldItemEntity): HeldItem {
        return HeldItem(
            item = heldItemDetailMapper(entity.item)
        )
    }
}