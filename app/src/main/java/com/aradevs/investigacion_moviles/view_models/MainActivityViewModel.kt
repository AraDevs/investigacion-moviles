package com.aradevs.investigacion_moviles.view_models

import androidx.lifecycle.ViewModel
import com.aradevs.storagemanager.use_cases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val saveBinnacleUseCase: SaveBinnacleUseCase,
    private val getBinnaclesUseCase: GetBinnaclesUseCase,
    private val deleteBinnacleUseCase: DeleteBinnacleUseCase,
    private val saveCompanyUseCase: SaveCompanyUseCase,
    private val getCompanyUseCase: GetCompanyUseCase,
    private val deleteCompaniesUseCase: DeleteCompaniesUseCase,
) : ViewModel() {

}