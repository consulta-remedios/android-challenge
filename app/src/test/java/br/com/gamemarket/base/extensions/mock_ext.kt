package com.consultaremedios.base.extensions

import org.mockito.Mockito

inline fun <reified T> mockito(): Lazy<T> = lazy {
    Mockito.mock(T::class.java)
}