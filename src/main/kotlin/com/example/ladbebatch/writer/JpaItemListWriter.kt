package com.example.ladbebatch.writer

import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.beans.factory.annotation.Autowired

class JpaItemListWriter<T>(
    @Autowired
    val jpaItemWriter: JpaItemWriter<T>
): JpaItemWriter<List<T>>() {

    override fun write(items: MutableList<out List<T>>) {
        val totalList = mutableListOf<T>()

        for (list in items)
            totalList.addAll(list)

        jpaItemWriter.write(totalList)
    }
}
