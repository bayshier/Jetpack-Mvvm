package com.jetpack.bean

import com.chad.library.adapter.base.entity.MultiItemEntity
import com.jetpack.constants.Constants

class ArticleBean {
    var curPage = 0
    var offset = 0
    var over = false
    var pageCount = 0
    var size = 0
    var total = 0
    var datas: MutableList<DatasBean>? = null

    class DatasBean() : MultiItemEntity {
//
//        override val itemType: Int
//            get() = if (envelopePic.isNullOrEmpty()) {
//                Constants.ITEM_ARTICLE
//            } else {
//                Constants.ITEM_ARTICLE_PIC
//            }

        override fun getItemType(): Int {
            return if (envelopePic.isNullOrEmpty()) {
                Constants.ITEM_ARTICLE
            } else {
                Constants.ITEM_ARTICLE_PIC
            }
        }

        /**
         * apkLink :
         * audit : 1
         * author :
         * canEdit : false
         * chapterId : 502
         * chapterName : 自助
         * collect : false
         * courseId : 13
         * desc :
         * descMd :
         * envelopePic :
         * fresh : false
         * id : 12154
         * link : https://juejin.im/post/5e5b50eb6fb9a07cae136773
         * niceDate : 2020-03-02 09:32
         * niceShareDate : 2020-03-02 09:32
         * origin :
         * prefix :
         * projectLink :
         * publishTime : 1583112725000
         * selfVisible : 0
         * shareDate : 1583112725000
         * shareUser : JsonChao
         * superChapterId : 494
         * superChapterName : 广场Tab
         * tags : []
         * title : 【建议收藏】2020年中高级Android大厂面试秘籍，为你保驾护航金三银四，直通大厂
         * type : 0
         * userId : 611
         * visible : 1
         * zan : 0
         */
        var apkLink: String? = null
        var audit = 0
        var author: String? = null
        var canEdit = false
        var chapterId = 0
        var chapterName: String? = null
        var collect = false
        var courseId = 0
        var desc: String? = null
        var descMd: String? = null
        var envelopePic: String? = null
        var fresh = false
        var id = 0
        var link: String? = null
        var niceDate: String? = null
        var niceShareDate: String? = null
        var origin: String? = null
        var prefix: String? = null
        var projectLink: String? = null
        var publishTime: Long = 0
        var selfVisible = 0
        var shareDate: Long = 0
        var shareUser: String? = null
        var superChapterId = 0
        var superChapterName: String? = null
        var title: String? = null
        var type = 0
        var userId = 0
        var visible = 0
        var zan = 0
        var tags: List<*>? = null
    }
}