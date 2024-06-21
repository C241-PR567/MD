package com.dicoding.md.data.retrofit

import com.dicoding.md.data.response.DetailDiseaseResponse
import com.dicoding.md.data.response.DiseaseResponse
import com.dicoding.md.data.response.ProfileUploadResponse
import com.dicoding.md.data.response.ResetPasswordRequest
import com.dicoding.md.data.response.ResetPasswordResponse
import com.dicoding.md.data.response.SignInRequest
import com.dicoding.md.data.response.SignInResponse
import com.dicoding.md.data.response.SignOutResponse
import com.dicoding.md.data.response.SignUpRequest
import com.dicoding.md.data.response.SignUpResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("/signin")
    fun signIn(
        @Body signInRequest: SignInRequest
    ): Call<SignInResponse>


    @POST("/signup")
    fun signUp(
        @Body signUpRequest: SignUpRequest
    ): Call<SignUpResponse>

    @GET("/signin/{uid}")
    fun getDataUser(
        @Path("uid") uid: String
    ): Call<SignInResponse>

    @POST("/signout")
    fun signOut(): Call<SignOutResponse>

    @POST("/reset-password")
    fun resetPassword(
        @Body request: ResetPasswordRequest
    ): Call<ResetPasswordResponse>

    @GET("list-disease")
    fun getDiseaseList(): Call<DiseaseResponse>

    @GET("list-diseases/{name}")
    fun searchDisease(
        @Query("name") query: String
    ): Call<DiseaseResponse>

    @GET("list-diseases/{id}")
    fun getDetailDiseaseById(
        @Path("id") id: String
    ): Call<DetailDiseaseResponse>

    @Multipart
    @POST("upload-profile-picture")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("uid") uid: RequestBody
    ): ProfileUploadResponse

}