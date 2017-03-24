package io.github.droidknights.droidknightsgoods.view.anim

import android.support.v4.view.ViewPager
import android.view.View

/**
 * Created by Tae-hwan on 24/03/2017.
 */

class ZoomOutPageTransformer : ViewPager.PageTransformer {

    val MIN_SCALE = 0.9F
    val MIN_ALPHA = 0.8F

    override fun transformPage(page: View?, position: Float) {
        page?.apply {
            if (position <= 1) {
                val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                val verticalMargin = height * ((1 - scaleFactor) / 2)
                val horizontalMargin = width * (1 - scaleFactor) / 2

                if (position < 0) {
                    translationX = horizontalMargin - verticalMargin / 2

                } else {
                    translationX = -horizontalMargin + verticalMargin / 2
                }

                scaleX = scaleFactor
                scaleY = scaleFactor

                alpha = MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)

            } else {
                alpha = 0F
            }
        }
    }
}