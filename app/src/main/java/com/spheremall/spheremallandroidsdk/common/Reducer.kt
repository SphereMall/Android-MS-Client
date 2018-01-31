package com.spheremall.spheremallandroidsdk.common

interface Reducer<STATE : Contract.State, in ACTION : Contract.Action> {
    fun reduce(state: STATE, action: ACTION): STATE
}