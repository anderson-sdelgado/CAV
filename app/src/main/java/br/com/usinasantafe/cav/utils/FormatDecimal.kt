package br.com.usinasantafe.cav.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

fun stringToDouble(value: String): Double {
    val locale = Locale.Builder().setLanguage("pt").setRegion("BR").build()
    val formatNumber = NumberFormat.getInstance(locale)
    val number = formatNumber.parse(value)!!
    return number.toDouble()
}

fun doubleToString(value: Double, decimalPlaces: Int = 1): String {
    val locale = Locale.Builder().setLanguage("pt").setRegion("BR").build()
    val symbols = DecimalFormatSymbols(locale)
    val pattern = buildString {
        append("#,##0")
        if (decimalPlaces > 0) {
            append(".")
            repeat(decimalPlaces) { append("0") }
        }
    }
    val format = DecimalFormat(pattern, symbols)
    return format.format(value)
}