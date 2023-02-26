package com.example.ladbebatch.common

import org.springframework.stereotype.Component

@Component
class DataShareBean <T> {

    lateinit var shareDataMap: MutableMap<String, T>

    fun DataShareBean () {
        this.shareDataMap = mutableMapOf()
    }

    fun putData(key: String, data: T) {
        shareDataMap.put(key, data) ?: logger().error("Map is not initialize")
    }

    fun getData(key: String): T {
        return shareDataMap[key] ?: throw NullPointerException("DataShareBean is Null!")
    }

    fun getSize(): Int {
        return if (shareDataMap.isEmpty()) {
            logger().error("Map is not initialize")
            0
        } else
            shareDataMap.size
    }
}
