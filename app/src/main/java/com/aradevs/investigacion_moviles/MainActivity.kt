package com.aradevs.investigacion_moviles

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aradevs.domain.coroutines.Status
import com.aradevs.investigacion_moviles.databinding.ActivityMainBinding
import com.aradevs.investigacion_moviles.dialogs.AddCompanyDialog
import com.aradevs.investigacion_moviles.view_models.MainActivityViewModel
import com.c3rberuss.androidutils.gone
import com.c3rberuss.androidutils.visible
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeBinnacleStatus()
        observeCompanyStatus()
        viewModel.getBinnacles()
        viewModel.getCompany()
    }

    private fun observeBinnacleStatus() {
        viewModel.binnacleStatus.observe(this) {
            when (it) {
                is Status.Success -> {
                    binding.loader.root.gone()
                }
                is Status.Error -> {
                    binding.loader.root.gone()
                }
                is Status.Loading -> binding.loader.root.visible()
                else -> {
                    //do nothing
                }
            }
        }
    }

    private fun observeCompanyStatus() {
        viewModel.companyStatus.observe(this) {
            when (it) {
                is Status.Success -> {
                    if (it.data == null) {
                        AddCompanyDialog.newInstance { company ->
                            viewModel.saveCompany(company)
                        }.show(supportFragmentManager, "add company")
                    }
                }
                is Status.Error -> {
                    Timber.d("Error ${it.exception}")
                }
                is Status.Loading -> {}
                else -> {
                    //do nothing
                }
            }
        }
    }

}