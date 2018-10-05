package com.github.fcopardo.easyrest.common

import kotlinx.serialization.Serializable

@Serializable
data class MLCountries(var id : String, var name : String, var locale : String, var currency_id : String)