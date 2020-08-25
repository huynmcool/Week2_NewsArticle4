package com.john117.week2_newsarticle4.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.john117.week2_newsarticle4.R
import com.john117.week2_newsarticle4.model.Story
import com.john117.week2_newsarticle4.ui.activity.DetailActivity
import com.john117.week2_newsarticle4.ui.adapter.StoryAdapter
import com.john117.week2_newsarticle4.utils.arrSection
import com.john117.week2_newsarticle4.viewmodel.StoryViewModel
import kotlinx.android.synthetic.main.dialog_filter.view.*
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private val storyAdapter: StoryAdapter by lazy {
        StoryAdapter { story ->
            itemStoryClick(story)
        }
    }

    private lateinit var storyViewModel: StoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeContainer.setColorSchemeResources(
            R.color.colorBtn
        )
        progressBarHome.visibility = View.VISIBLE
        inItView()
        bindings()
        addEvent()
    }

    private fun addEvent() {
        btn_menu_section.setOnClickListener {
            showDialogSection()
        }
        swipeContainer.setOnRefreshListener { refreshLayout() }
    }

    private fun refreshLayout() {
        Handler().postDelayed(
            {
                storyViewModel.getListStories(storyViewModel.getSection())
                swipeContainer.isRefreshing = false
            }, 1000
        )
    }

    @SuppressLint("InflateParams")
    private fun showDialogSection() {
        val mBottomSheetDialog = RoundedBottomSheetDialog(requireContext())
        val section_filter = layoutInflater.inflate(R.layout.section_filter, null)

        var section = ""

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.arr_section,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            section_filter.spinner_section.adapter = adapter
        }

        section_filter.spinner_section.setSelection(arrSection.indexOf(storyViewModel.getSection()))

        section_filter.spinner_section.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    section = parent?.getItemAtPosition(position).toString()
                }
            }

        section_filter.btn_save.setOnClickListener {
            storyViewModel.setSection(section)
            storyViewModel.getListStories(section)
            mBottomSheetDialog.dismiss()
            progressBarHome.visibility = View.VISIBLE
        }

        ///////////////////////////////////////////////////
        section_filter.btn_cancel.setOnClickListener {
            mBottomSheetDialog.dismiss()
        }

        mBottomSheetDialog.setContentView(section_filter)
        mBottomSheetDialog.show()

        section_filter.btnCancelDialog.setOnClickListener{
            mBottomSheetDialog.dismiss()
        }
    }

    private fun bindings() {
        storyViewModel = activity?.run {
            ViewModelProviders.of(this)[StoryViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        storyViewModel.getListStories(arrSection[7])
        progressBarHome.visibility = View.VISIBLE
        storyViewModel.listStoryLiveData.observe(this@HomeFragment, Observer { listStories ->
            storyAdapter.addDataStories(listStories)
            progressBarHome.visibility = View.GONE
        })
    }

    private fun inItView() {
//        rv_story.adapter = storyAdapter
//        rv_story.layoutManager = GridLayoutManager(requireContext(), 4)
//        rv_story.addItemDecoration(GridItemDecoration(10, 4))

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rv_story.layoutManager = staggeredGridLayoutManager
        rv_story.adapter = storyAdapter
    }

    private fun itemStoryClick(story: Story) {
        val intent = Intent(activity, DetailActivity.getInstance().javaClass)
        intent.putExtra("iSearch", false)
        intent.putExtra("story", story)
        startActivity(intent)
    }
}