package com.jetpack.ui

import android.animation.ValueAnimator
import android.os.Build
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.base_library.common.albumById
import com.base_library.common.loadBlurTrans
import com.jetpack.common.AnimUtil
import com.jetpack.play.PlayerManager
import com.jetpack.view.FloatPlayLayout

/**
 * des 播放dataBinding适配器
 *   /6/28
 *  
 */
object PlayBindAdapter {


    /**
     * 加载图片,做高斯模糊处理
     */
    @BindingAdapter(value = ["imgPlayBlur"])
    @JvmStatic
    fun imgPlayBlur(view: ImageView, albumId: Long) {
        if (albumId == -1L){
            view.setImageResource(0)
            return
        }
        view.loadBlurTrans(view.context.applicationContext, albumById(albumId), 90)
    }

    /**
     * 收藏
     */
    @BindingAdapter(value = ["imgCollect"])
    @JvmStatic
    fun imgCollect(view: ImageView, isCollect: Boolean) {
        view.isSelected = isCollect
    }

    /**
     * 处理暂停/播放
     */
    @BindingAdapter(value = ["imgPlay"])
    @JvmStatic
    fun imgPlay(view: ImageView, playStatus: Int) {
        when(playStatus){
            //停止
            PlayerManager.RELEASE,PlayerManager.PAUSE->{
                view.isSelected = false
            }
            //播放
            PlayerManager.START, PlayerManager.RESUME->{
                view.isSelected = true
            }
        }
    }

    /**
     * 悬浮-处理暂停/播放
     */
    @BindingAdapter(value = ["floatImgPlay"])
    @JvmStatic
    fun floatImgPlay(view: FloatPlayLayout, playStatus: Int?) {
        when(playStatus){
            //停止
            PlayerManager.RELEASE,PlayerManager.PAUSE->{
                view.setImgPlaying(false)
            }
            //播放
            PlayerManager.START, PlayerManager.RESUME->{
                view.setImgPlaying(true)
            }
        }
    }

    /**
     * 悬浮-歌名
     */
    @BindingAdapter(value = ["floatSongName"])
    @JvmStatic
    fun floatSongName(view: FloatPlayLayout, songName: String?) {
        view.setSongName(songName)
    }

    /**
     * 悬浮-图片
     */
    @BindingAdapter(value = ["floatImgAlbum"])
    @JvmStatic
    fun floatImgAlbum(view: FloatPlayLayout, albumId: Long?) {
        view.setAlbumPic(albumId)
    }

    /**
     * 旋转动画
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    @BindingAdapter(value = ["rotate"])
    @JvmStatic
    fun rotate(view: View, playStatus: Int) {
        if (anim==null){
            anim = AnimUtil.getRepeatRotate(view,20000)
        }
        when(playStatus){
            PlayerManager.RELEASE->{
                //目前啥也不做
            }
            PlayerManager.START,PlayerManager.RESUME->{
                //动画已经启动
                if (anim?.isPaused!!){
                    anim?.resume()
                }
                //动画未启动，直接启动
                else{
                    anim?.start()
                }
            }

            PlayerManager.PAUSE->{
                anim?.pause()
            }
        }
    }

    /**
     * 图片动画
     */
    var anim: ValueAnimator? = null

}