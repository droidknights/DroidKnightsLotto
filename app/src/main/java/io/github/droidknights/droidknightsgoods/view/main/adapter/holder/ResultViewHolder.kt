package io.github.droidknights.droidknightsgoods.view.main.adapter.holder

import android.animation.Animator
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.droidknights.droidknightsgoods.R
import io.github.droidknights.droidknightsgoods.listener.OnLottoClickListener
import io.github.droidknights.droidknightsgoods.model.Lotto
import kotlinx.android.synthetic.main.item_result_view_holder.view.*

/**
 * Created by tae-hwan on 23/03/2017.
 */

class ResultViewHolder(val context: Context, parent: ViewGroup?, val onLottoClickListener: OnLottoClickListener) :
        RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_result_view_holder, parent, false)) {

    var animValue = 0
    var duration = 250L

    fun onBindView(position: Int, lotto: Lotto) {
        animValue = 0
        itemView?.apply {
            if (lotto.isInterval) {
                tv_result_idx.text = 0.toString()
                var interval = lotto.idx / 10
                if (interval <= 0) {
                    interval = 1
                }
                startAnimation(lotto.idx, interval)
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

    private fun startAnimation(destValue: Int, interval: Int) {
        itemView?.apply {
            if (tv_result_idx.text.toString().toInt() == destValue) {
                Log.d("TEMP", "animValue $animValue destValue $destValue")
                tv_result_idx.animate().cancel()
            } else {
                tv_result_idx.animate().translationY(0F).setDuration(duration).setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        animValue += interval
                        if (animValue > destValue) {
                            animValue = destValue
                        }
                        tv_result_idx.text = animValue.toString()
                        tv_result_idx.translationY = 0F
                        if (animValue + interval != destValue) {
                            duration -= 20L
                            if (duration <= 0) { duration = 10L }
                            startAnimation(destValue, interval)
                        } else {
                            tv_result_idx.text = destValue.toString()
                            animation?.cancel()
                        }
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationStart(animation: Animator?) {
                    }
                }).start()
            }
        }
    }
}