package com.uvg.ana.booktribev2.network

import com.uvg.ana.booktribev2.BuildConfig
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object SupabaseClient {

    private val supabaseUrl: String = BuildConfig.SUPABASE_URL
    private val supabaseKey: String = BuildConfig.SUPABASE_KEY

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    // Initialize Supabase client with the retrieved values
    val client = createSupabaseClient(
        supabaseUrl = supabaseUrl,
        supabaseKey = supabaseKey
    ) {
        install(GoTrue)

    }
}

