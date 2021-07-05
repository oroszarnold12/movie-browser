package com.example.ubbassignment2.service

import java.lang.Exception

class ServiceException: Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
}