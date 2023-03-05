package com.example.newsfragment.mapper

interface Mapper<I, O> {
    fun map(input: I):O
}