package com.spheremall.spheremallandroidsdk.common

import io.reactivex.Observable


interface Contract {

    interface View

    interface Presenter<in VIEW : View> {

        fun onViewCreated(view: VIEW)
        fun onViewDestroyed()
    }

    interface Model<STATE : State> {
        fun init()

        fun release()

        val state: Observable<STATE>

        fun stateObservable(): Observable<STATE>
    }

    interface State

    interface Action
}