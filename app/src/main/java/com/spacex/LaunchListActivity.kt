package com.spacex

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.spacex.model.Launch
import com.spacex.util.ContentUtil
import com.spacex.viewmodel.SpaceXLaunchViewModel
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*


/**
 * An activity representing a list of SpaceX Launches. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [LaunchDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class LaunchListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private val mViewModel: SpaceXLaunchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        setSupportActionBar(toolbar)
        toolbar.title = title


        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        mViewModel.getLaunches().observe(this, Observer<List<Launch>?> {
            progressbar.visibility = View.GONE
            item_list.adapter = SimpleItemRecyclerViewAdapter(this, it!!, twoPane)
        })

    }

    class SimpleItemRecyclerViewAdapter(private val parentActivity: LaunchListActivity,
                                        private val values: List<Launch>,
                                        private val twoPane: Boolean) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as Launch
                ContentUtil.selectedItem = item
                if (twoPane) {
                    val fragment = LaunchDetailFragment()
                    parentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, LaunchDetailActivity::class.java)
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.missionName.text = item.missionName
            holder.rocketName.text = item.rocket?.rocketName
            holder.siteName.text = item.launchSite?.siteName
            holder.launchDate.text = item.launchDate?.toString()
            Glide.with(parentActivity).load(item.links?.missionPatchSmall).into(holder.launchImg);
            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val missionName: TextView = view.mission_name
            val rocketName: TextView = view.rocket_name
            val siteName: TextView = view.site_name
            val launchImg: ImageView = view.imageView
            val launchDate: TextView = view.launch_date
        }
    }
}
