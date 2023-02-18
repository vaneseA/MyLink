package com.example.mylink.ui.component

class SjUtil {
    companion object{
        fun checkUrlPrefix(url:String):Boolean{
            return url.startsWith("https://") || url.startsWith("http://")
        }
    }
}