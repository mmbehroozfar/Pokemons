package com.mmb.data.mapper

import com.mmb.data.local.model.AbilityEntity
import com.mmb.data.remote.model.AbilityResponse
import com.mmb.domain.model.Ability
import javax.inject.Inject

class AbilityMapper @Inject constructor(
    private val abilityDetailMapper: AbilityDetailMapper,
) {

    operator fun invoke(response: AbilityResponse): AbilityEntity {
        return AbilityEntity(
            ability = abilityDetailMapper(response.ability),
            isHidden = response.isHidden,
            slot = response.slot,
        )
    }

    operator fun invoke(entity: AbilityEntity): Ability {
        return Ability(
            ability = abilityDetailMapper(entity.ability),
            isHidden = entity.isHidden,
            slot = entity.slot,
        )
    }
}