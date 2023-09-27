package com.example.ladbebatch.common.bean

import com.example.ladbebatch.common.logger
import com.google.common.collect.Maps
import org.springframework.stereotype.Component

@Component
class DataListShareBean<T> {

    var shareDataListMap: MutableMap<String, MutableList<T>> = Maps.newConcurrentMap()

    fun init(key: String, data: List<T>) {
        if (shareDataListMap[key] != null)
            logger().info("Already {} is alive", key)
        else
            shareDataListMap[key] = data.toMutableList()
    }



    fun putData(key: String, data: T) {
        if (shareDataListMap[key] != null) {
            val list: MutableList<T> = shareDataListMap.getOrDefault(key, ArrayList()).toMutableList()
            list.add(data)
            shareDataListMap[key] = list
        } else
            logger().error("Map is not initialize")
    }

    fun putDataList(key: String, data: List<T>) {
        if (shareDataListMap[key] != null) {
            val list: MutableList<T> = shareDataListMap.getOrDefault(key, ArrayList()).toMutableList()
            list.addAll(data)
            shareDataListMap[key] = list
        } else
            logger().error("Map is not initialize")
    }

    fun removeItem(key: String, data: T): MutableList<T> {
        val list = getDataList(key)
        if (list.remove(data))
            shareDataListMap[key] = list
        else
            logger().error("This Data {} can't remove", data)
        return list
    }

    fun getDataList(key: String): MutableList<T> {
        return shareDataListMap[key] ?: emptyList<T>().toMutableList()
    }

    fun getSize(key: String): Int {
        return if (shareDataListMap[key] == null) {
            0
        } else
            shareDataListMap.size
    }
}
