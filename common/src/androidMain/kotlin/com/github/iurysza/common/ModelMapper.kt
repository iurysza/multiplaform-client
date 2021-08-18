package com.github.iurysza.common

import com.github.iurysza.vaccinationtracker.cache.CovidVaccinationData
import com.github.iurysza.vaccinationtracker.entity.VaccinationDataResponseItem
import java.text.NumberFormat

fun List<VaccinationDataResponseItem>.fromAverage14DaysToUiModel(
//  context: Context,
//  getIconDrawable: (Context, String) -> Int
): List<StateVaccinationCardModel> = map {
  val average14days = it.average14Days
  StateVaccinationCardModel(
//    icon = getIconDrawable(context, it.isoCode),
    name = it.state,
    coverage = 0f,
    dataList = listOf(
      DataPoint(
        average14days.firstDose.toLong().formatNumber(),
        "1ª dose"
      ),
      DataPoint(
        average14days.secondDose.toLong().formatNumber(),
        "2ª dose"
      ),
      DataPoint(
        average14days.singleDose.toLong().formatNumber(),
        "Dose única"
      ),
      DataPoint(
        average14days.perMillion.toLong().formatNumber(),
        "Doses/milhão"
      ),
      DataPoint(
        average14days.total.toLong().formatNumber(),
        "Total"
      ),
    )
  )
}

fun List<VaccinationDataResponseItem>.fromDailyToUiModel(
//  context: Context,
//  getIconDrawable: (Context, String) -> Int
): List<StateVaccinationCardModel> = mapNotNull {
  val dailyVaccinations = it.dailyVaccinations
  dailyVaccinations.total ?: return@mapNotNull null

  StateVaccinationCardModel(
//    icon = getIconDrawable(context, it.isoCode),
    name = it.state,
    coverage = 0f,
    dataList = listOf(
      DataPoint(
        dailyVaccinations.firstDose?.toLong().formatNumber(),
        "1ª dose"
      ),
      DataPoint(
        dailyVaccinations.secondDose?.toLong().formatNumber(),
        "2ª dose"
      ),
      DataPoint(
        dailyVaccinations.singleDose?.toLong().formatNumber(),
        "Dose única"
      ),
      DataPoint(
        dailyVaccinations.fullyVaccinated?.toLong().formatNumber(),
        "Imunizados"
      ),
      DataPoint(
        dailyVaccinations.total?.toLong().formatNumber(),
        "Total"
      ),
    )
  )
}

fun List<CovidVaccinationData>.mapToUiModel(
//  context: Context,
//  getIconDrawable: (Context, String) -> Int
): List<StateVaccinationCardModel> {
  return map {
    StateVaccinationCardModel(
//      icon = getIconDrawable(context, it.isoCode),
      name = it.state,
      coverage = it.fullyVaccinatedPercentage.toFloat() / 100,
      dataList = listOf(
        DataPoint(
          it.firstDose.formatNumber(),
          "1ª dose"
        ),
        DataPoint(
          "${it.firstDosePercentage}%",
          "1ª dose"
        ),
        DataPoint(
          it.secondDose.formatNumber(),
          "2ª dose"
        ),
        DataPoint(
          "${it.secondDosePercentage}%",
          "2ª dose"
        ),
        DataPoint(
          it.fullyVaccinated.formatNumber(),
          "Imunizados"
        ),
        DataPoint(
          "${it.fullyVaccinatedPercentage}%",
          "Imunizados"
        ),
      )
    )

  }
}

fun Long?.formatNumber(): String {
  return runCatching { NumberFormat.getInstance().format(this) }.getOrDefault("0")
}

