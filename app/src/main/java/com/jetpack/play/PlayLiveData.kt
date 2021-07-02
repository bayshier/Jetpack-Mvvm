package com.jetpack.play

import androidx.lifecycle.MutableLiveData
import com.jetpack.play.bean.AudioBean
import com.jetpack.play.bean.ProgressBean


class PlayLiveData {

    /**
     * 歌曲信息
     */
    val audioLiveData = MutableLiveData<AudioBean>()


    /**
     * 播放状态,目前有四种。可根据类型进行扩展
     * release
     * start
     * resume
     * pause
     */
    val playStatusLiveData = MutableLiveData<Int>()

    /**
     * 当前播放进度
     */
    val progressLiveData = MutableLiveData<ProgressBean>()

    /**
     * 播放模式
     */
    val playModeLiveData = MutableLiveData<Int>()

    /**
     * 重置
     */
    val resetLiveData = MutableLiveData<Int>()

}