package com.antonioleiva.weatherapp.extensions

import java.util.*

/**
 * 。针对插入
我们使用另外一个Anko函数，它需要一个表名和一个 vararg 修饰
的 Pair<String, Any> 作为参数。这个函数会把 vararg 转换成Android SDK需
要的 ContentValues 对象。所以我们的任务组成是把 map 转换成一
个 vararg 数组。我们为 MutableMap 创建了一个扩展函数：
 */
fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()

inline fun <T, R : Any> Iterable<T>.firstResult(predicate: (T) -> R?): R {
    for (element in this) {
        val result = predicate(element)
        if (result != null) return result
    }
    throw NoSuchElementException("No element matching predicate was found.")
}