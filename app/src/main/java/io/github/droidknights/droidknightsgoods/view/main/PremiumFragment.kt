package io.github.droidknights.droidknightsgoods.view.main

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import io.github.droidknights.droidknightsgoods.R
import io.github.droidknights.droidknightsgoods.listener.OnPremiumClickListener
import io.github.droidknights.droidknightsgoods.model.Premium
import io.github.droidknights.droidknightsgoods.view.main.presenter.PremiumContract
import io.github.droidknights.droidknightsgoods.view.main.presenter.PremiumPresenter
import kotlinx.android.synthetic.main.fragment_premium.*
import tech.thdev.base.view.BasePresenterFragment

/**
 * Created by Tae-hwan on 24/03/2017.
 */

class PremiumFragment : BasePresenterFragment<PremiumContract.View, PremiumContract.Presenter>(), PremiumContract.View {

    private var position: Int = -1
    private lateinit var premium: Premium
    private lateinit var onPremiumClickListener: OnPremiumClickListener

    companion object {
        fun create(position: Int, premium: Premium, onPremiumClickListener: OnPremiumClickListener): PremiumFragment {
            val fragment = PremiumFragment()
            fragment.position = position
            fragment.premium = premium
            fragment.onPremiumClickListener = onPremiumClickListener
            return fragment
        }
    }

    override fun getLayout() = R.layout.fragment_premium

    override fun onCreatePresenter() = PremiumPresenter()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateItemView()
    }

    fun updateItemView() {
        presenter?.updateView(premium)
    }

    override fun showItem(sponsors: String, count: Int, unit: String, productName: String, imageRes: Int) {
        android.util.Log.w("TEMP", "showItem $position")
        img_done_button.visibility = View.GONE

        tv_sponsors.text = ""
        if (sponsors.isNotEmpty()) {
            tv_sponsors.text = sponsors
        }
        tv_count.text = "$count$unit"
        tv_product_name.text = productName

        Glide.with(this)
                .load(imageRes)
                .fitCenter()
                .crossFade()
                .into(img_gifts)

        if (count == 0) {
            img_done_button.visibility = View.VISIBLE
            img_gifts.setOnClickListener { }
        } else {
            img_gifts.setOnClickListener {
                onPremiumClickListener.onClick(position)
            }
        }
    }
}