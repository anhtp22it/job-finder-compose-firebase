package com.tpanh.jobfinder.repository.impl

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.tpanh.jobfinder.model.Education
import com.tpanh.jobfinder.model.Language
import com.tpanh.jobfinder.model.User
import com.tpanh.jobfinder.model.WorkExperience
import com.tpanh.jobfinder.repository.UserRepository
import kotlinx.coroutines.tasks.await

class UserRepositoryImpl(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth,

): UserRepository {
    override suspend fun getCurrentUser(): User {
        val userId = auth.currentUser?.uid
        var user = User()
        fireStore.collection("users")
            .document(userId!!)
            .get()
            .addOnSuccessListener {
                user = it.toObject(User::class.java)!!
            }
            .addOnFailureListener {
                Log.e("UserRepositoryImpl", "Error getting documents.", it)
            }.await()
        return user
    }

    override suspend fun updateUser(user: User) {
        val userId = auth.currentUser?.uid
        Log.d("UserRepositoryImpl", "updateUser: $user")
        fireStore.collection("users")
            .document(userId!!)
            .set(user)
            .addOnSuccessListener {
                Log.d("UserRepositoryImpl", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener {
                Log.e("UserRepositoryImpl", "Error writing document", it)
            }.await()
    }

    override suspend fun updateWorkExperience(workExperience: WorkExperience) {
        val userId = auth.currentUser?.uid
        fireStore.collection("users")
            .document(userId!!)
            .update("experience", workExperience)
            .addOnSuccessListener {
                Log.d("UserRepositoryImpl", "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.e("UserRepositoryImpl", "Error updating document", e)
            }.await()
    }

    override suspend fun updateEducation(education: Education) {
        val userId = auth.currentUser?.uid
        fireStore.collection("users")
            .document(userId!!)
            .update("education", education)
            .addOnSuccessListener {
                Log.d("UserRepositoryImpl", "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.e("UserRepositoryImpl", "Error updating document", e)
            }.await()
    }

    override suspend fun updateLanguages(languages: List<Language>) {
        val userId = auth.currentUser?.uid
        fireStore.collection("users")
            .document(userId!!)
            .update("languages", languages)
            .addOnSuccessListener {
                Log.d("UserRepositoryImpl", "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.e("UserRepositoryImpl", "Error updating document", e)
            }.await()
    }

    override suspend fun getSavedJobs(): List<String> {
        val userId = auth.currentUser?.uid
        var savedJobs = listOf<String>()
        fireStore.collection("users")
            .document(userId!!)
            .get()
            .addOnSuccessListener { document ->
                val user = document.toObject(User::class.java)
                savedJobs = user?.saveJobs ?: listOf()
            }
            .addOnFailureListener {
                Log.e("UserRepositoryImpl", "Error getting documents.", it)
            }.await()
        return savedJobs
    }

    override suspend fun getUserById(userId: String): User {
        var user = User()
        fireStore.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener {
                user = it.toObject(User::class.java)!!
            }
            .addOnFailureListener {
                Log.e("UserRepositoryImpl", "Error getting documents.", it)
            }.await()
        return user
    }

    override suspend fun saveJob(jobId: String) {
        val userId = auth.currentUser?.uid
        fireStore.collection("users")
            .document(userId!!)
            .update("saveJobs", FieldValue.arrayUnion(jobId))
            .addOnSuccessListener {
                Log.d("UserRepositoryImpl", "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.e("UserRepositoryImpl", "Error updating document", e)
            }.await()
    }

    override suspend fun removeSavedJob(jobId: String) {
        val userId = auth.currentUser?.uid
        fireStore.collection("users")
            .document(userId!!)
            .update("saveJobs", FieldValue.arrayRemove(jobId))
            .addOnSuccessListener {
                Log.d("UserRepositoryImpl", "DocumentSnapshot successfully updated!")
            }
            .addOnFailureListener { e ->
                Log.e("UserRepositoryImpl", "Error updating document", e)
            }.await()
    }

}