package com.example.diapplication.domain.usecase

import com.example.diapplication.data.repository.ClientRepository
import com.example.diapplication.domain.entity.UserGeocoding
import javax.inject.Inject

class UpdateUserGeocodingUseCase @Inject constructor(private val clientRepository: ClientRepository) {
    suspend operator fun invoke(latitude: Double, longitude: Double): UserGeocoding {
        return clientRepository.getClientService(latitude, longitude)
    }
}