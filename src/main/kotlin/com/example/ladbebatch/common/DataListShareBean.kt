package com.example.ladbebatch.common

import com.google.common.collect.Maps
import org.springframework.stereotype.Component

@Component
class DataListShareBean<T> {

    lateinit var shareDataListMap: MutableMap<String, List<T>>

    fun DataListShareBean() {
        this.shareDataListMap = Maps.newConcurrentMap()
    }

    fun putData(key: String, data: T) {
        if (shareDataListMap[key] != null) {
            val list: MutableList<T> = shareDataListMap.getOrDefault(key, ArrayList()).toMutableList()
            list.add(data)
            shareDataListMap[key] = list
        } else
            logger().error("Map is not initialize")
    }

    fun putListData(key: String, data: List<T>) {
        if (shareDataListMap[key] != null) {
            val list: MutableList<T> = shareDataListMap.getOrDefault(key, ArrayList()).toMutableList()
            list.addAll(data)
            shareDataListMap[key] = list
        } else
            logger().error("Map is not initialize")
    }

    fun getData(key: String): List<T> {
        return shareDataListMap[key] ?: throw NullPointerException("No Data!!")
    }

    fun getSize(): Int {
        return if (shareDataListMap.isEmpty()) {
            logger().error("Map is not initialize")
            0
        } else
            shareDataListMap.size
    }
}
