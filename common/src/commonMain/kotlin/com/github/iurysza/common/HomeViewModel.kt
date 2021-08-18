package com.github.iurysza.common

import androidx.compose.runtime.Immutable
import com.github.iurysza.common.ui.home.HomeScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow

abstract class HomeViewModel  {
   val scope: CoroutineScope = GlobalScope
   val state = MutableStateFlow<HomeScreenState>(HomeScreenState.Loading)
   val expandedCardIdsList = MutableStateFlow(emptyList<String>())
   val bottomSheetState = MutableStateFlow<BottomSheetModel?>(null)
   val totalToggle = MutableStateFlow(true)
   val isSortEnabled = MutableStateFlow(false)
   val dailyToggle = MutableStateFlow(false)
   val sortedByValue = MutableStateFlow(false)
   val average14daysToggle = MutableStateFlow(false)


   abstract fun getTotalVaccinationData(forceReload: Boolean=false)

  abstract fun get14DaysAverageVaccinationData()

  abstract fun getDailyVaccinationData()

  abstract fun onItemClicked(stateVaccinationCardModel: StateVaccinationCardModel)

  abstract fun toggleSort()

  abstract fun refresh()

  abstract fun onToggleExpand(itemId: String)

  abstract fun onDismiss()
}

@Immutable
data class StateVaccinationCardModel(
  val icon: Int = 0,
  val coverage: Float,
  val name: String,
  val dataList: List<DataPoint>
)

@Immutable
data class DataPoint(
  val value: String,
  val label: String,
)

data class BottomSheetModel(
  val name: String,
  val lastUpdate:String,
  val sourceName: String,
  val sourceWebsite: String,
)