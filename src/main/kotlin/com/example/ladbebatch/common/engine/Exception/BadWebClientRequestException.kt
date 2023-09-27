package com.example.ladbebatch.common.engine.Exception

import reactor.core.publisher.Mono

class BadWebClientRequestException(
    val statusCode: Int,
    val statusText: String? = null
) : RuntimeException() {

//    constructor(statusCode: Int) : super() {
//        this.statusCode = statusCode
//    }
//
//    constructor(statusCode: Int, msg: String?) : super(msg) {
//        this.statusCode = statusCode
//    }
//
//    constructor(statusCode: Int, msg: String?, statusText: String?) : super(msg) {
//        this.statusCode = statusCode
//        this.statusText = statusText
//    }
}
