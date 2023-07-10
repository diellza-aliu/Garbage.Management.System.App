package com.example.garbagemanagementsystemapp.data

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.garbagemanagementsystemapp.ApplicationFirebaseAuth
import com.example.garbagemanagementsystemapp.admin_screen.create_driver.DriverModel
import com.example.garbagemanagementsystemapp.admin_screen.user_details.UserModel
import com.example.garbagemanagementsystemapp.user_screen.my_complaints.ComplaintsModel
import com.example.garbagemanagementsystemapp.user_screen.my_profile.MyProfileModel
import com.example.garbagemanagementsystemapp.util.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseStore: FirebaseFirestore
): AuthRepository {
    val TAG = "Firebase"
    override suspend fun loginUser(email: String, password: String): SignInResponse{
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Log.d(TAG, "loginUser: success")
            Response.Success(true)
        } catch (e: Exception) {
            Log.d(TAG, "loginUser: catch ${e.localizedMessage}")
            Toast.makeText(ApplicationFirebaseAuth.context, "Something went wrong. You are not registered as a user!", Toast.LENGTH_LONG).show()
            Response.Error(e)
        }
    }

    override suspend fun registerUser(name: String, surname: String, email: String, phone: String, password: String, confirmPassword: String): SignUpResponse {
        return try {
            if (!email.lowercase().contains("admin") && !email.lowercase().contains("driver")) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                if (firebaseAuth.currentUser != null) {
                    val documentReference = firebaseStore.collection("Users").document(firebaseAuth.currentUser!!.uid)
                    val userInfo = HashMap<String, String>()
                    userInfo["FullName"] = "$name $surname"
                    userInfo["Name"] = name
                    userInfo["Phone"] = phone
                    userInfo["Email"] = email
                    //Specify if the user is citizen
                    userInfo["Type"] = "1"
                    documentReference.set(userInfo)
                }
                Response.Success(true)
            }else{
                Toast.makeText(ApplicationFirebaseAuth.context, "You cannot be registered as an admin or a driver", Toast.LENGTH_LONG).show()
                Response.Error(Exception())
            }
        }catch (e: Exception) {
            Toast.makeText(ApplicationFirebaseAuth.context, "Something went wrong while registering!", Toast.LENGTH_LONG).show()
            Response.Error(e)
        }
    }
    override fun signOut() = firebaseAuth.signOut()

    override fun getAuthState(viewModelScope: CoroutineScope) = callbackFlow {
        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), firebaseAuth.currentUser == null)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun retrieveData(): MyProfileModel {
        var fullName = ""
        var email = ""
        var name = ""
        var type = ""
            val documentReference = firebaseStore.collection("Users").document(firebaseAuth.currentUser!!.uid)
            runBlocking {
                fullName = getStringValue(documentReference, "FullName")
                email = getStringValue(documentReference, "Email")
                name = getStringValue(documentReference, "Name")
                type = getStringValue(documentReference, "Type")
            }
        return MyProfileModel(email, fullName, name, type)
    }

    override fun getCurrentUser(): String {
        return try {
            val documentReference = firebaseStore.collection("Users").document(firebaseAuth.currentUser?.uid ?: "0")
            runBlocking {
                getStringValue(documentReference, "Type")
            }
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun registerBin(binId: String, city: String, location: String, loadType: String, status: String) {
        try {
                if (firebaseAuth.currentUser != null) {
                    val documentReference = firebaseStore.collection("Bins").document("${firebaseAuth.currentUser?.uid}${Random().nextInt(100)}")
                    val userInfo = HashMap<String, String>()
                    userInfo["BinId"] = binId
                    userInfo["City"] = city
                    userInfo["LoadType"] = loadType
                    userInfo["Location"] = location
                    userInfo["Status"] = status
                    documentReference.set(userInfo)
                }
                Toast.makeText(ApplicationFirebaseAuth.context, "Complaint registered successfully", Toast.LENGTH_LONG).show()
                Response.Error(Exception())
        }catch (e: Exception) {
            Response.Error(e)
        }
    }
    override suspend fun updateBin(binId: String, status: String) {
        try {
            if (firebaseAuth.currentUser != null) {
                runBlocking {
                    val documentReference = firebaseStore.collection("Bins").whereEqualTo("BinId", binId).get().await().documents[0].reference
                    documentReference.update("Status", status)
                }
            }
            Toast.makeText(ApplicationFirebaseAuth.context, "You cannot be registered as an admin or a driver", Toast.LENGTH_LONG).show()
            Response.Error(Exception())
        }catch (e: Exception) {
            Response.Error(e)
        }
    }
    override suspend fun updateProfile(name : String, fullName : String, email : String) {
        try {
            if (firebaseAuth.currentUser != null) {
                runBlocking {
                    val documentReference = firebaseStore.collection("Users").document(firebaseAuth.currentUser!!.uid)
                    documentReference.update("Name", name)
                    documentReference.update("FullName", fullName)
                    documentReference.update("Email", email)
                }
            }
            Toast.makeText(ApplicationFirebaseAuth.context, "You cannot be registered as an admin or a driver", Toast.LENGTH_LONG).show()
            Response.Error(Exception())
        }catch (e: Exception) {
            Response.Error(e)
        }
    }

    suspend fun getStringValue(documentReference: DocumentReference, fieldName: String): String {
        return try {
            val documentSnapshot = documentReference.get().await()
            documentSnapshot.getString(fieldName).toString()
        } catch (e: Exception) {
            throw e
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun fetchMyComplaints(): MutableList<ComplaintsModel> {
        val minimumDocumentPath = "${firebaseAuth.currentUser?.uid}000"
        val maximumDocumentPath = "${firebaseAuth.currentUser?.uid}999"
        val documentSnapshots = mutableListOf<ComplaintsModel>()
        if (firebaseAuth.currentUser != null) {
            val documentReference = firebaseStore.collection("Bins").whereGreaterThanOrEqualTo(FieldPath.documentId(), minimumDocumentPath)
                .whereLessThanOrEqualTo(FieldPath.documentId(), maximumDocumentPath)
            runBlocking {
                documentReference.get().await().documents.forEach { data ->
                    val binId = data.getString("BinId").toString()
                    val city = data.getString("City").toString()
                    val status = data.getString("Status").toString()
                    val loadType = data.getString("LoadType").toString()
                    val location = data.getString("Location").toString()
                    documentSnapshots.add(ComplaintsModel(binId, city, status, loadType, location))
                }
            }
        }
        return documentSnapshots
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun fetchAllComplaints(): MutableList<ComplaintsModel> {
        val documentSnapshots = mutableListOf<ComplaintsModel>()
        if (firebaseAuth.currentUser != null) {
            val documentReference = firebaseStore.collection("Bins")
            runBlocking {
                documentReference.get().await().documents.forEach { data ->
                    val binId = data.getString("BinId").toString()
                    val city = data.getString("City").toString()
                    val status = data.getString("Status").toString()
                    val loadType = data.getString("LoadType").toString()
                    val location = data.getString("Location").toString()
                    documentSnapshots.add(ComplaintsModel(binId, city, status, loadType, location))
                }
            }
        }
        return documentSnapshots
    }

    override suspend fun createDriver(email: String, password : String, fullName: String, name: String, type: String): SignUpResponse {
        return try {
                firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                if (firebaseAuth.currentUser != null) {
                    val documentReference = firebaseStore.collection("Users").document(firebaseAuth.currentUser!!.uid)
                    val userInfo = HashMap<String, String>()
                    userInfo["Email"] = email
                    userInfo["FullName"] = fullName
                    userInfo["Name"] = name
                    userInfo["Type"] = type
                    documentReference.set(userInfo)
                }
                Response.Success(true)
        }catch (e: Exception) {
            Response.Error(e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun fetchAllDrivers(): MutableList<DriverModel> {
        val documentSnapshots = mutableListOf<DriverModel>()
        if (firebaseAuth.currentUser != null) {
            val documentReference = firebaseStore.collection("Users")
            runBlocking {
                val querySnapshot = documentReference.whereEqualTo("Type", "3").get().await()
                querySnapshot.documents.forEach { data ->
                    val email = data.getString("Email").toString()
                    val fullName = data.getString("FullName").toString()
                    val name = data.getString("Name").toString()
                    val type = data.getString("Type").toString()
                    documentSnapshots.add(DriverModel(email, fullName, name, type))
                }
            }
        }
        return documentSnapshots
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun fetchAllUsers(): MutableList<UserModel> {
        val documentSnapshots = mutableListOf<UserModel>()
        if (firebaseAuth.currentUser != null) {
            val documentReference = firebaseStore.collection("Users")
            runBlocking {
                val querySnapshot = documentReference.whereEqualTo("Type", "1").get().await()
                querySnapshot.documents.forEach { data ->
                    val email = data.getString("Email").toString()
                    val fullName = data.getString("FullName").toString()
                    val name = data.getString("Name").toString()
                    val phone = data.getString("Phone").toString()
                    val type = data.getString("Type").toString()
                    documentSnapshots.add(UserModel(email, fullName, name, phone, type))
                }
            }
        }
        return documentSnapshots
    }

}