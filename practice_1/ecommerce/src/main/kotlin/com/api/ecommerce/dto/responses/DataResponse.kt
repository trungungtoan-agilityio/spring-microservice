package com.api.ecommerce.dto.responses

import java.io.Serializable

class DataResponse<T>(val code: Int) : Serializable {
    private var data: Any? = null
    constructor(code: Int, data: Any) : this(code) {
        this.data = data
    }
}