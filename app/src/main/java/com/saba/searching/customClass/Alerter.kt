package com.saba.searching.customClass

import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.blankj.utilcode.util.ActivityUtils
import com.irozon.sneaker.Sneaker
import com.saba.searching.R

class Alerter(status: Status, title: String?, message: String, messageClick: String? = null, duration: Int = 4000) {

    init {
        val activity = ActivityUtils.getTopActivity()
        when (status) {
            Status.E -> {

                Sneaker.with(activity) // Activity, Fragment or ViewGroup
                    .setTitle(title ?: "خطا", R.color.colorWhite) // Title and title color
                    .setMessage(message, R.color.colorWhite) // Message and message color
                    .setDuration(duration) // Time duration to show
                    .autoHide(true) // Auto hide Sneaker view
                    .setCornerRadius(9)
                    .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT) // Height of the Sneaker layout
                    .setTypeface(
                        ResourcesCompat.getFont(
                            activity,
                            R.font.iransansmobile
                        )!!
                    ) // Custom font for title and message
                        .autoHide(true)
                    .sneak(R.color.colorRed) // Sneak with background color

            }

            Status.I -> {

                Sneaker.with(activity) // Activity, Fragment or ViewGroup
                    .setTitle(title ?: "موفق", R.color.colorWhite) // Title and title color
                    .setMessage(message, R.color.colorWhite) // Message and message color
                    .setDuration(duration) // Time duration to show
                    .autoHide(true) // Auto hide Sneaker view
                    .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT) // Height of the Sneaker layout
                    .setTypeface(
                        ResourcesCompat.getFont(
                            activity,
                            R.font.iransansmobile
                        )!!
                    ) // Custom font for title and message
                    .setCornerRadius(9) // Radius and margin for round corner Sneaker. - Version 1.0.2
                    .sneak(R.color.colorAccent) // Sneak with background color

            }
            Status.W -> {

                Sneaker.with(activity) // Activity, Fragment or ViewGroup
                    .setTitle(title ?: "توجه", R.color.colorWhite) // Title and title color
                    .setMessage(message, R.color.colorWhite) // Message and message color
                    .setDuration(duration) // Time duration to show
                    .autoHide(true) // Auto hide Sneaker view
                    .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT) // Height of the Sneaker layout
                    .setTypeface(
                        ResourcesCompat.getFont(
                            activity,
                            R.font.iransansmobile
                        )!!
                    ) // Custom font for title and message
                    .setCornerRadius(9) // Radius and margin for round corner Sneaker. - Version 1.0.2
                    .sneak(R.color.colorYellow) // Sneak with background color

            }
            else -> {}
        }
    }

    enum class Status {
        E, I, W, Click
    }

    interface Click {
        fun onClickSnack()
    }
}
