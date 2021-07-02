package com.base_library.http

/**
 * 用来封装业务错误信息
 *
 *  
 *   -05-09
 */
class ApiException(val errorMessage: String, val errorCode: Int) :
    Throwable()