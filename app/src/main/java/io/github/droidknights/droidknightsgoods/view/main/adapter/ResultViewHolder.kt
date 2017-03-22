package io.github.droidknights.droidknightsgoods.view.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.droidknights.droidknightsgoods.R
import io.github.droidknights.droidknightsgoods.model.Lotto
import kotlinx.android.synthetic.main.item_result_view_holder.view.*

/**
 * Created by tae-hwan on 23/03/2017.
 */

class ResultViewHolder(val context: Context, parent: ViewGroup?) :
        RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_result_view_holder, parent, false)) {

    fun onBindView(position: Int, lotto: Lotto) {
        itemView?.apply {
            tv_result_idx.text = lotto.idx.toString()
        }
    }
}