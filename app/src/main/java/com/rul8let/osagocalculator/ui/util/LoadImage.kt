package com.rul8let.osagocalculator.ui.util

import android.widget.ImageView
import coil.decode.SvgDecoder
import coil.load

fun ImageView.loadSvg(urlSVG: String?) {
    this.load(urlSVG){
        decoderFactory(SvgDecoder.Factory())
    }
}