package com.spacex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.spacex.util.ContentUtil
import kotlinx.android.synthetic.main.item_detail.*


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [LaunchListActivity]
 * in two-pane mode (on tablets) or a [LaunchDetailActivity]
 * on handsets.
 */
class LaunchDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ContentUtil.selectedItem.let {
            Glide.with(this).load(it?.links?.missionPatch).into(launch_image);
            rocket_name.text = it?.rocket?.rocketName
            rocket_type.text = it?.rocket?.rocketType
            first_stage_core_serial.text = it?.rocket?.firstStage?.cores?.get(0)?.coreSerial
            nationality.text = it?.rocket?.secondStage?.payloads?.get(0)?.nationality
            manufacturer.text = it?.rocket?.secondStage?.payloads?.get(0)?.manufacturer
            site_name.text = it?.launchSite?.siteName
            launch_date.text = it?.launchDate?.toString()
            launch_video.text = it?.links?.videoLink
            wiki_link.text = it?.links?.wikipedia
            article_link.text = it?.links?.articleLink


        }
    }

}
