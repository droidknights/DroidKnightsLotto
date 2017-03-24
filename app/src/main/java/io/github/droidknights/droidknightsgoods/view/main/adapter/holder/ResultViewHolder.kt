package io.github.droidknights.droidknightsgoods.view.main.adapter.holder

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import io.github.droidknights.droidknightsgoods.R
import io.github.droidknights.droidknightsgoods.listener.OnLottoClickListener
import io.github.droidknights.droidknightsgoods.model.ConstLimit
import io.github.droidknights.droidknightsgoods.model.Lotto
import kotlinx.android.synthetic.main.item_result_view_holder.view.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by tae-hwan on 23/03/2017.
 */

class ResultViewHolder(val context: Context, parent: ViewGroup?, val onLottoClickListener: OnLottoClickListener) :
        RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_result_view_holder, parent, false)) {

    val ran = Random(System.nanoTime())
    val duration = TimeUnit.SECONDS.toMillis(2)

    fun onBindView(position: Int, lotto: Lotto) {
        itemView?.apply {
            if (lotto.isAnimationNumber) {
                startAnimation(lotto.idx)
            } else {
                tv_result_idx.text = lotto.idx.toString()
            }
            tv_result_idx.isChecked = false
            tv_result_idx.setOnClickListener {
                Log.d("TEMP", "click ${lotto.premiumIdx}")
                onLottoClickListener.onClickItem(lotto.premiumIdx, tv_result_idx.isChecked)
            }
        }
    }

    private fun startAnimation(destValue: Int) {
        itemView?.apply {
            tv_result_idx.animate()
                    .setDuration(duration)
                    .setInterpolator(DecelerateInterpolator())
                    .withStartAction {
                        tv_result_idx.setTextColor(ContextCompat.getColor(context, R.color.textRanHint))
                        tv_result_idx.text = 0.toString()
                    }
                    .setUpdateListener { tv_result_idx.text = ran.nextInt(ConstLimit()).toString() }
                    .withEndAction {
                        tv_result_idx.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
                        tv_result_idx.text = destValue.toString()
                    }
                    .start()
        }
    }
}