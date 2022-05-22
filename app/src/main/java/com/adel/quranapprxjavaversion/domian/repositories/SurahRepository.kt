package com.adel.quranapprxjavaversion.domian.repositories

import com.adel.quranapprxjavaversion.data.models.ApiResponse
import com.adel.quranapprxjavaversion.data.models.SurahDetailsModel
import com.adel.quranapprxjavaversion.data.models.SurahModel
import io.reactivex.rxjava3.core.Single

interface SurahRepository {
    fun getAllSurah(): Single<ApiResponse<List<SurahModel>>>
    fun getSurahDetails(surahNum: Int): Single<ApiResponse<SurahDetailsModel>>
}
