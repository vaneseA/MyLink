package com.example.mylink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylink.SjDomain
import com.example.mylink.SjLink
import com.example.mylink.SjTag

class ViewLinkViewModel : ViewModel() {
    var _linkList = MutableLiveData<ArrayList<SjLink>>()
    val linkList: LiveData<ArrayList<SjLink>> get() = _linkList!!

    fun loadDatas() {
        var youtube = SjDomain("유튜브", "https://m.youtube.com/watch?")
        var allchan = SjDomain("올찬식탁", "https://m.smartstore.naver.com/allchanfood/products/")

        var bread = SjTag("빵")

        var pokemonBread = SjLink("돌아온 포켓몬빵", allchan, "6333541088", bread)
        var pieceCake = SjLink("조각 케이크", allchan, "718907248", bread)
        var ytnNews = SjLink("포켓몬빵 뉴스", youtube, "v=GJ3a33gdx7o", bread)

        _linkList.postValue(
            ArrayList<SjLink>().apply {
                add(pokemonBread)
                add(pieceCake)
                add(ytnNews)
            }
        )
    }
}