package com.github.iurysza.common

import com.github.iurysza.common.ui.home.HomeScreenState
import com.github.iurysza.vaccinationtracker.VaccinationTracker
import com.github.iurysza.vaccinationtracker.entity.VaccinationDataResponseItem
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AppHomeViewModel(
  private val sdk: VaccinationTracker
) : HomeViewModel() {

  private val fullVaccinationDataCache: Deferred<List<VaccinationDataResponseItem>> by lazy {
    scope.async {
      // state.emit(HomeScreenState.Loading)
      sdk.getFullVaccinationData()
    }
  }

  init {
    scope.launch {
      fullVaccinationDataCache.await()
    }
    getTotalVaccinationData(false)
  }

  override fun getTotalVaccinationData(forceReload: Boolean) {
    scope.launch(Dispatchers.IO) {

      isSortEnabled.emit(true)
      totalToggle.emit(true)
      average14daysToggle.emit(false)
      dailyToggle.emit(false)

      runCatching {
        sdk.getVaccinationData(latest = forceReload).mapToUiModel()
      }.onSuccess {
        state.emit(HomeScreenState.Success(it))
      }.onFailure { state.emit(HomeScreenState.Error) }
    }
  }

  override fun get14DaysAverageVaccinationData() {
    scope.launch(Dispatchers.IO) {
      average14daysToggle.emit(true)
      isSortEnabled.emit(false)
      totalToggle.emit(false)
      dailyToggle.emit(false)

      runCatching {
        fullVaccinationDataCache.await().fromAverage14DaysToUiModel()
      }.onSuccess {
        state.emit(HomeScreenState.Success(it))
      }.onFailure {
        state.emit(HomeScreenState.Error)
      }
    }
  }

  override fun getDailyVaccinationData() {
    scope.launch(Dispatchers.IO) {
      dailyToggle.emit(true)
      isSortEnabled.emit(false)
      average14daysToggle.emit(false)
      totalToggle.emit(false)
      onDismiss()

      runCatching {
        fullVaccinationDataCache.await().fromDailyToUiModel()
      }.onSuccess {
        state.emit(HomeScreenState.Success(it))
      }.onFailure {
        state.emit(HomeScreenState.Error)
      }
    }
  }

  override fun onItemClicked(stateVaccinationCardModel: StateVaccinationCardModel) {
    scope.launch {
      val name = stateVaccinationCardModel.name
      bottomSheetState.emit(
        fullVaccinationDataCache.await().find { it.state == name }?.let {
          BottomSheetModel(
            name = name,
            sourceName = it.sourceName,
            sourceWebsite = it.sourceWebsite,
            lastUpdate = it.lastUpdateDate
          )
        }
      )
    }
  }

  override fun toggleSort() {
    val modelList = (state.value as? HomeScreenState.Success)?.modelList ?: return

    scope.launch(Dispatchers.Default) {
      state.emit(HomeScreenState.Success(
        if (sortedByValue.value) {
          sortedByValue.emit(false)
          modelList.sortedBy { it.name }
        } else {
          sortedByValue.emit(true)
          modelList.sortedByDescending { it.coverage }
        }))
    }
  }

  override fun refresh() {
    when {
      totalToggle.value -> getTotalVaccinationData(true)
      average14daysToggle.value -> get14DaysAverageVaccinationData()
      dailyToggle.value -> getDailyVaccinationData()
    }
  }

  override fun onToggleExpand(itemId: String) {
    expandedCardIdsList.value = expandedCardIdsList.value.toMutableList().also { list ->
      if (list.contains(itemId)) {
        list.remove(itemId)
      } else {
        list.add(itemId)
      }
    }
  }

  override fun onDismiss() {
    scope.launch {
      // bottomSheetState.emit(null)
    }
  }
}

//private val getDrawableByName: (Context, String) -> Int = { context, isoCode ->
//  context.resources.getIdentifier(
//    "ic_flag_$isoCode",
//    "drawable",
//    context.packageName
//  )
//}

//sealed class HomeScreenState {
//  data class Success(val modelList: List<StateVaccinationCardModel>) : HomeScreenState()
//  object Loading : HomeScreenState()
//  object Error : HomeScreenState()
//}
