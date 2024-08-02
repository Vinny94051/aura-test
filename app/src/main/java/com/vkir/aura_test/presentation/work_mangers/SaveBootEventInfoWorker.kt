package com.vkir.aura_test.presentation.work_mangers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.vkir.aura_test.domain.SaveEventInfoUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveBootEventInfoWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    private val saveEventInfoUseCase: SaveEventInfoUseCase by inject()

    override suspend fun doWork(): Result {
        val currentMills = System.currentTimeMillis()
        saveEventInfoUseCase.saveEventInfoUseCase(currentMills)
        return Result.success()
    }
}
