package com.jetpack.play.bean

data class ProgressBean(
    /**
     * 当前时间
     */
    var currentDuration: Int = 0,

    /**
     * 总时间
     */
    var totalDuration: Int = 0
)