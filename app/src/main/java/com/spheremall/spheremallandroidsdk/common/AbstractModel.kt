package com.spheremall.spheremallandroidsdk.common

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

abstract class AbstractModel<STATE : Contract.State, in ACTION : Contract.Action>(defaultState: STATE) : Contract.Model<STATE> {

    override val state: BehaviorSubject<STATE> = BehaviorSubject.createDefault(defaultState)

    override fun stateObservable(): Observable<STATE> = state

    abstract fun reducer(): Reducer<STATE, ACTION>

    protected fun action(action: ACTION) {
        state.onNext(
                reducer()
                        .reduce(state = state.value!!, action = action))
    }
}