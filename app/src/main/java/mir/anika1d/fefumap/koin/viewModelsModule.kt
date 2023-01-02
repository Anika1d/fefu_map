package mir.anika1d.fefumap.koin

import mir.anika1d.fefumap.viewmodels.MainViewModels
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<MainViewModels> {MainViewModels()}

}