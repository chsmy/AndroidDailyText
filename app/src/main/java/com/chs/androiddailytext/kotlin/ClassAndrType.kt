package com.chs.androiddailytext.kotlin

/**
 *  @author chs
 *  date: 2019-12-19 17:21
 *  des: 数据类型
 */

var istrue:Boolean = true
var isfalse:Boolean = false

var isInt:Int = 8
val isFloat:Float = 2.0f
val isLong:Long = 123

val isDouble:Double = 2.0
val isShort:Short = 123

val isByte:Byte = 127

val isChar:Char = '0'
val isChar1:Char = '中'

val isString:String = "sss"
val isStringi:String = String(charArrayOf('1','2'))
val isString2:String="""
    dafaf 
    dafa 
""".trimIndent()
//kotlin中字符串比较，==相当于java中的equal  ===相当于java中的==

//kotlin 中所有的类都继承自 Any这个类
open class Person(var name:String,var age:Int){
    init {
        println("ren name:$name,age:$age")
    }
}
class Student(name:String, age:Int) : Person(name,age)

fun getName():String?{
    return null
}

val person = Student("",1)
//加问好之后  如果强转错误返回null  不加问好 会直接报错
val person1 = person as? Person

val range = 0..1024 //[0,1024]
val range1 = 0 until 1024 //[0.1024)

val intArry = intArrayOf(1,2,3)
val charArry = charArrayOf('2','3')
fun main(args:Array<String>){
    println(isInt)
    val student = Student("Divad",18)

    println(getName()?.length)
}