package com.example.administrator.kotlin.util

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Administrator on 2017/7/24 0024.
 * 这个委托可以作用在任何非null的类型。它接收任何类型的引用，然后像getter和
    setter那样使用T。现在我们需要去实现这些函数。
    Getter函数 如果已经被初始化，则会返回一个值，否则会抛异常。
    Setter函数 如果仍然是null，则赋值，否则会抛异常。
 */
class NotNullSingleValueVar<T>:ReadWriteProperty<Any?,T> {
    private var value:T?=null

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException(" already initialized")

    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException("" +
                "not initialized")

    }
}