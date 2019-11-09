package com.sanmed.android.rxandroid

import org.junit.Test

import org.junit.Assert.*

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.logging.Level
import java.util.logging.Logger

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun repeatPrintRx() {
        Observable.just(5,6,7).map { ";-)".repeat(it) }.subscribe{println(it)}
    }

    @Test
    fun repeatPrintFilterRx() {
        Observable.just(5,6,7).map { ";-)".repeat(it) }.filter { it.length<16 }.subscribe{println(it)}
    }

    @Test
    fun collectionsFilterRx() {
        println(listOf(5,6,7))
        println( listOf(5,6,7).map { it * 5})
        println( listOf(5,6,7).map { it * 5}.filter { it > 25 })
    }

    @Test
    fun collectionsSequenceFilterRx() {
        println(listOf(5,6,7))
        println( listOf(5,6,7).asSequence().map { it * 5}.toList())
        println( listOf(5,6,7).asSequence().map { it * 5}.filter { it > 25 }.toList())
    }

    @Test
    fun collectionsSchedulersFilterRx() {
        Observable.just(5,6,7).subscribeOn(Schedulers.io()).map { ";-)".repeat(it) }.subscribe{println(it)}
    }

    @Test
    fun observableCreateRx() {


        val observable = Observable.create<Int>{subscriber ->
            println("Created")
            subscriber.onNext(5)
            subscriber.onNext(6)
            subscriber.onNext(7)
            subscriber.onComplete()
            println("Completed")
        }
        observable.subscribe{ println(it)}
        println("done")

    }

    @Test
    fun observableCreateWithObserverRx() {
        val observable = Observable.create<Int>{subscriber ->
            println("Created")
            subscriber.onNext(5)
            subscriber.onNext(6)
            subscriber.onNext(7)
            subscriber.onComplete()
            println("Completed")
        }

        val observer = object : Observer<Int> {
            override fun onComplete() {
                println("onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                println("on Subscribe")
            }

            override fun onNext(t: Int) {
                println("onNext $t")
            }

            override fun onError(e: Throwable) {
                println(e)
            }
        }

        observable.subscribe(observer)
        println("done")

    }
}
