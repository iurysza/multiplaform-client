package com.github.iurysza.common.ui.home

import com.github.iurysza.common.StateVaccinationCardModel


sealed class HomeScreenState {
  data class Success(val modelList: List<StateVaccinationCardModel>) : HomeScreenState()
  object Loading : HomeScreenState()
  object Error : HomeScreenState()
}
