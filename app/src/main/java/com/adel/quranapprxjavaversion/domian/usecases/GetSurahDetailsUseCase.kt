package com.adel.quranapprxjavaversion.domian.usecases

import com.adel.quranapprxjavaversion.data.utils.ErrorHandlerImpl
import com.adel.quranapprxjavaversion.data.models.ApiResponse
import com.adel.quranapprxjavaversion.data.models.SurahDetailsModel
import com.adel.quranapprxjavaversion.data.repositories.SurahRepositoryImpl
import com.adel.quranapprxjavaversion.domian.entities.Result
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetSurahDetailsUseCase @Inject constructor(
    private val repository: SurahRepositoryImpl,
    private val errorHandler: ErrorHandlerImpl
) {

    operator fun invoke(surahNum: Int): Single<Result<ApiResponse<SurahDetailsModel>>> =
        repository.getSurahDetails(surahNum)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { Result.Success(it) as Result<ApiResponse<SurahDetailsModel>> }
            .onErrorReturn { Result.Error(errorHandler.getError(it)) }
}
