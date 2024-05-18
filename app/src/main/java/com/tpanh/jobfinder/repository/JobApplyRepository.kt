package com.tpanh.jobfinder.repository

import com.tpanh.jobfinder.model.JobApply

interface JobApplyRepository {
    suspend fun applyJob(apply: JobApply)
    suspend fun getMyApplies(): List<JobApply>
    suspend fun getApplyById(id: String): JobApply
}