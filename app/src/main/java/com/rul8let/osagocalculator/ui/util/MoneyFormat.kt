package com.rul8let.osagocalculator.ui.util

object MoneyFormat {
    fun manyFormat(s : String) : String = buildString {
            for (i in s.indices) {
                if ((s.length-i) % 3 == 0) append(' ')
                append(s[i])
            }
        }
}