package com.john117.week2_newsarticle4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.john117.week2_newsarticle4.api.BaseReponse
import com.john117.week2_newsarticle4.model.Doc
import com.john117.week2_newsarticle4.model.Story
import com.john117.week2_newsarticle4.utils.arrSection

class StoryViewModel : ViewModel() {
    var listStoryLiveData = MutableLiveData<ArrayList<Story>>().apply { value = arrayListOf() }
    var listSearchStoryLiveData =
        MutableLiveData<ArrayList<Doc>>().apply { value = arrayListOf() }

    private var section = MutableLiveData<String>()
    private var section_filter = MutableLiveData<String>()
    private var beginDay = MutableLiveData<String>()
    private var endDay = MutableLiveData<String>()
    private var sortOrderID = MutableLiveData<Int>()
    private var sortStr= MutableLiveData<String>()


    private val baseReponse: BaseReponse by lazy { BaseReponse() }

    // CompositeDisposable dùng để quản lý Disposable, được sinh ra để chứa tất cả các Disposable
    // Disposable: là một Subscription mới
    // Subscription: mỗi khi có thực hiện 1 Observable sau đó chuyển qua 1 subcriber xử lý sẽ trả về 1 Subscription
    private val composite by lazy { CompositeDisposable() }
    var listTempt = ArrayList<Story>()

    init {
        if (listStoryLiveData.value.isNullOrEmpty())
            getListStories(section.value)
    }

    fun initValue(){
        section_filter.value= arrSection[0]
        section.value = arrSection[0]
        beginDay.value = String()
        endDay.value = String()
        sortOrderID.value = -1
        sortStr.value = String()
    }

    fun getListStories(section: String?) {
        listTempt = arrayListOf()
        composite.add(
            baseReponse.getListStory(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (!it.results.isNullOrEmpty()) {
                        it.results.forEach { item->listTempt.add(item) }
                        listStoryLiveData.postValue(listTempt)
                    }
                }, {
                })
        )
    }

    fun getListSearchSize():Int{
        return listSearchStoryLiveData.value?.size ?: 0
    }

    fun getListSearchStory(strSearch: String) {
        composite.add(
            baseReponse.getListSearchStory(strSearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listSearchStoryLiveData.postValue(it.response?.docs)
                }, {
                })
        )
    }

    fun getListFilter(
        strSearch: String,
        beginDate: String,
        endDate: String,
        sort: String,
        section:String
    ) {
        composite.add(
            baseReponse.getListFilterStory(strSearch, beginDate, endDate, sort,section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    listSearchStoryLiveData.postValue(it.response?.docs)
                }, {
                })
        )
    }

    fun getSection(): String {
        return section.value.toString()
    }

    fun setSection(sec: String) {
        section.value = sec
    }

    fun getNewDesk():String{
        return section_filter.value.toString()
    }

    fun setNewDesk(sec:String){
        section_filter.value=sec
    }

    fun getBeginDate(): String {
        return beginDay.value.toString()
    }

    fun setBeginDate(beginDate: String) {
        beginDay.value = beginDate
    }

    fun getEndDate(): String {
        return endDay.value.toString()
    }

    fun setEndDate(endDate: String) {
        endDay.value = endDate
    }

    fun getSortOrder(): Int? {
        return sortOrderID.value
    }

    fun setSortOrder(id: Int) {
        sortOrderID.value = id
    }

    fun getSortStr(): String {
        return sortStr.value.toString()
    }

    fun setSortStr(str: String) {
        sortStr.value = str
    }

}