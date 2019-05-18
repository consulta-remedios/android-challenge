package com.consultaremedios.base.extensions

import org.mockito.Mockito

inline fun <reified T> mock(): Lazy<T> = lazy {
    Mockito.mock(T::class.java)
}