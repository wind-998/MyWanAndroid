package com.example.happyfragment.network

import android.util.Log
import okhttp3.*
import okio.Buffer

class Intecepter: Interceptor {
    override fun intercept(chain :Interceptor.Chain): Response {
        val time_start: Long = System.nanoTime()
        val request : Request = chain.request( )
        val response : Response = chain.proceed(request)

        val buffer = Buffer()
        request.body?.writeTo(buffer)
        val requestbodystr = buffer.readUtf8()

        Log.e(  "OKHTTP" ,String.format("Sending request %S with params %s", request.url,requestbodystr))

        //必须创建一个全新的reponse返回，不能使用旧的来回去信息
        val bussinessData : String = response.body?.string() ?:"response body null"
        val mediaType : MediaType? = response.body?.contentType()
        val newBody : ResponseBody = ResponseBody.create(mediaType, bussinessData)
        val newResponse: Response = response.newBuilder().body(newBody).build()
        return newResponse

    }
}