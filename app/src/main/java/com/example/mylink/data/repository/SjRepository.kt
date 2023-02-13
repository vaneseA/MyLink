package com.example.mylink.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.mylink.data.LinkTagCrossRef
import com.example.mylink.data.SjDomain
import com.example.mylink.data.SjLink
import com.example.mylink.data.SjTag
import com.example.mylink.data.dao.SjDao
import com.example.mylink.data.db.SjDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SjRepository {
    val dao: SjDao = SjDatabase.getDao()
    val domains: LiveData<List<SjDomain>> = dao.getAllDomains()
    val links: LiveData<List<SjLink>> = dao.getAllLinks()
    val linksWithDomains = SjDatabase.getDao().getLinksAndDomain()
    val tags: LiveData<List<SjTag>> = dao.getAllTags()
    val domainNames: LiveData<List<String>> = dao.getAllDomainNames()

    fun insertDomain(newDomain: SjDomain) =
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertDomain(newDomain)
        }

    fun insertTag(newTag: SjTag) =
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertTag(newTag)
        }

    fun insertLink(domain: SjDomain, newLink: SjLink, tags: List<SjTag>) =
        CoroutineScope(Dispatchers.IO).launch {
            //set link domain
            newLink.did = domain.did

            //insert newLink
            val lid = async {
                insertLink(newLink)
            }

            //insert crossRef after newLink insert
            insertLinkTagCrossRefs(lid.await(), tags)
        }

    private suspend fun insertLink(newLink: SjLink): Int {
        return dao.insertLink(newLink).toInt()
    }

    private suspend fun insertLinkTagCrossRefs(lid: Int, tags: List<SjTag>) {
        val linkTagCrossRefs = mutableListOf<LinkTagCrossRef>()
        for (tag in tags) {
            linkTagCrossRefs.add(LinkTagCrossRef(lid = lid, tid = tag.tid))
        }
        dao.insertLinkTagCrossRefs(*linkTagCrossRefs.toTypedArray())
    }


    fun deleteDomain(domain: SjDomain) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteDomain(domain)
            //링크에서 도메인을 참조하고 있을 수 있으니,
            //확인하고, 있으면 지우는 작업을 하지 않고,
            //사용자에게 알릴 수 있으면 좋겠다.
        }
    }

    fun deleteLink(link: SjLink) {
        CoroutineScope(Dispatchers.IO).launch {
            val count = dao.countLinkTagCrossRefByLid(link.lid)
            if (count == 0) {
                dao.deleteLink(link)
            } else {
                //UI에 표시하면 좋을 것 같다.
                Log.i(javaClass.canonicalName, "link is referenced by TagCrossRef")
            }
        }
    }

    fun deleteTag(tag: SjTag) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteTag(tag)
            //링크와 태그 크로스 레프 객체에서 참조하고 있을 수 있으니,
            //마찬가지로 확인하고, 있으면 지우지 말고 알리기
        }
    }
}