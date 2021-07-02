package com.base_library.play

/**
 * des 播放状态,需要注入到播放控制器中,用于播放状态的回调
 *  
 * @data 2020-06-23
 */
interface IPlayerStatus {

    /**
     * 缓冲更新
     */
    fun onBufferingUpdate(percent: Int)

    /**
     * 播放结束
     */
    fun onComplete()

}