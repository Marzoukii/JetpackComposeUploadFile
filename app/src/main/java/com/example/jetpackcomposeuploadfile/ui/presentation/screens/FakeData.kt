package com.example.jetpackcomposeuploadfile.ui.presentation.screens

import com.example.myapp.domain.model.FileItem

object FakeData {

    val fileItems = listOf(
        FileItem(
            id = "1",
            name = "Documents",
            isDirectory = true,
            date = "10/09/2026",
            contentType = "application/pdf"
        ),
        FileItem(
            id = "2",
            parentId = "1",
            name = "CV_Chadi.pdf",
            isDirectory = false,
            date = "09/09/2026",
            size = 245_760,
            contentType = "application/pdf"
        ),
        FileItem(
            id = "3",
            parentId = "1",
            name = "Images",
            isDirectory = true,
            date = "08/09/2026"
        ),
        FileItem(
            id = "4",
            parentId = "1",
            name = "photo.jpg",
            isDirectory = false,
            date = "08/09/2026",
            size = 1_572_864,
            contentType = "image/jpeg"
        )
    )
}