package com.fghilmany.myapplication

interface Listener {
    fun onAddItem(data: Data, position: Int? = null)
    fun onEditItem(data: Data, position: Int? = null, isMove: Boolean)
    fun onRemoveItem(data: Data)
}