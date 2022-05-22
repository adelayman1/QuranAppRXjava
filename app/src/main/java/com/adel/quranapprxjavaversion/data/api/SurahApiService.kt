package com.adel.quranapprxjavaversion.data.api

import com.adel.quranapprxjavaversion.data.models.ApiResponse
import com.adel.quranapprxjavaversion.data.models.SurahDetailsModel
import com.adel.quranapprxjavaversion.data.models.SurahModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SurahApiService {
    @GET("surah")
    fun getAllSurah(): Single<ApiResponse<List<SurahModel>>>

    @GET("surah/{surahNum}/ar.alafasy")
    fun getSurahDetails(@Path("surahNum") surahNum: Int): Single<ApiResponse<SurahDetailsModel>>
}
