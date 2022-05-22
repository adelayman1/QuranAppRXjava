package com.adel.quranapprxjavaversion.data.repositories

import com.adel.quranapprxjavaversion.data.models.ApiResponse
import com.adel.quranapprxjavaversion.data.models.SurahDetailsModel
import com.adel.quranapprxjavaversion.data.models.SurahModel
import com.adel.quranapprxjavaversion.data.api.SurahApiService
import com.adel.quranapprxjavaversion.domian.repositories.SurahRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SurahRepositoryImpl @Inject constructor(var apiService: SurahApiService) : SurahRepository {
    override fun getAllSurah(): Single<ApiResponse<List<SurahModel>>> {
           return apiService.getAllSurah()
    }

    override fun getSurahDetails(surahNum: Int): Single<ApiResponse<SurahDetailsModel>> {
        return apiService.getSurahDetails(surahNum)
    }
}
