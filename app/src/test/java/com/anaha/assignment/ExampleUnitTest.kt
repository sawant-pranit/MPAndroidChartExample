package com.anaha.assignment

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)


        /*val result = listOf(1, 2, 3).fold(0) { sum, element ->
            print("values $sum $element")
            sum + element
        }*/
        val result = listOf(1, 2, 3).reduce { sum, element ->
            println("values $sum $element")
            sum + element
        }
        print("result :$result")

        val buf = StringBuffer()
    }

    @Test
    fun minAndMaxArray() {
        var array = arrayListOf<Int>(4, 2, 0, 8, 20, 9, 2)

        for(i in array.indices) {
            if(i < array.size -1 && array[i] > array[i+1]){
                val temp = array[i]
                array[i] = array[i+1]
                array[i+1] = temp
            }
        }
        print("Array $array")
    }



}