package com.john117.week2_newsarticle4.api

import retrofit2.Call
import com.john117.week2_newsarticle4.model.Stories
import com.john117.week2_newsarticle4.model.StorySearch
import com.john117.week2_newsarticle4.utils.API_KEY
import com.john117.week2_newsarticle4.utils.arrSection
import retrofit2.http.*


interface ApiManager {

    @GET("topstories/v2/{section}.json")
    fun getListStory(
        @Path("section") section: String = arrSection[0],
        @Query("api-key") token: String = API_KEY
    ): Call<Stories>

    @GET("search/v2/articlesearch.json")
    fun getListSearch(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = ""
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json")
    fun getListSearch1(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("begin_date") beginDate: String = "",
        @Query("end_date") endDate: String = "",
        @Query("sort") sort: String = ""
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json")
    fun getListSearch2(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("end_date") endDate: String = "",
        @Query("sort") sort: String = ""
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json")
    fun getListSearch3(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("begin_date") beginDate: String = "",
        @Query("sort") sort: String = ""
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json")
    fun getListSearch4(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("begin_date") beginDate: String = "",
        @Query("end_date") endDate: String = ""
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json")
    fun getListSearch5(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("sort") sort: String = ""
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json")
    fun getListSearch6(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("end_date") endDate: String = ""
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json")
    fun getListSearch7(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("begin_date") beginDate: String = ""
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json")
    fun getListSearch8(

        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("fq") strSection: String=arrSection[0]


    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json?fq=news_desk:({section})")
    fun getListSearch9(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("begin_date") beginDate: String = "",
        @Query("end_date") endDate: String = "",
        @Query("sort") sort: String = "",
        @Path("section") strSection: String = arrSection[0]
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json?fq=news_desk:({section})")
    fun getListSearch10(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("end_date") endDate: String = "",
        @Query("sort") sort: String = "",
        @Path("section") strSection: String = arrSection[0]
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json?fq=news_desk:({section})")
    fun getListSearch11(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("begin_date") beginDate: String = "",
        @Query("sort") sort: String = "",
        @Path("section") strSection: String = arrSection[0]
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json?fq=news_desk:({section})")
    fun getListSearch12(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("begin_date") beginDate: String = "",
        @Query("end_date") endDate: String = "",
        @Path("section") strSection: String = arrSection[0]
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json?fq=news_desk:({section})")
    fun getListSearch13(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("sort") sort: String = "",
        @Path("section") strSection: String = arrSection[0]
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json?fq=news_desk:({section})")
    fun getListSearch14(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("end_date") endDate: String = "",
        @Path("section") strSection: String = arrSection[0]
    ): Call<StorySearch>

    @GET("search/v2/articlesearch.json?fq=news_desk:({section})")
    fun getListSearch15(
        @Query("api-key") token: String = API_KEY,
        @Query("q") strSearch: String = "",
        @Query("begin_date") beginDate: String = "",
        @Path("section") strSection: String = arrSection[0]
    ): Call<StorySearch>
}