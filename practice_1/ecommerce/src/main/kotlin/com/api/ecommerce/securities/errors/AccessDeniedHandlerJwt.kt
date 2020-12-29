package com.api.ecommerce.securities.errors

import com.api.ecommerce.errors.ErrorResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.security.web.access.AccessDeniedHandler
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AccessDeniedHandlerJwt : AccessDeniedHandler {

    @Throws(IOException::class)
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: org.springframework.security.access.AccessDeniedException?
    ) {
        val errorResponse = request?.requestURI?.let {
            ErrorResponse.Builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message("Invalid Authorization token")
                .path(it)
                .build()
        }
        response?.status = HttpStatus.FORBIDDEN.value()
        response?.contentType = APPLICATION_JSON_VALUE
        response?.writer?.write(jacksonObjectMapper().writeValueAsString(errorResponse))
    }
}
