package com.spheremall.spheremallandroidsdk.common

import android.view.View

interface BaseViewHoldersFactory {
    fun holder(type: Int, view: View): BaseViewHolder<*>
}