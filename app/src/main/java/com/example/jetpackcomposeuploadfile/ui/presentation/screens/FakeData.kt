package com.example.jetpackcomposeuploadfile.ui.presentation.screens

import com.example.jetpackcomposeuploadfile.domain.model.FileItemModel


object FakeData {

    val fileItems = listOf(
        FileItemModel(
            id = "1",
            name = "Documents",
            isDirectory = true,
            date = "10/09/2026",
            contentType = "application/pdf"
        ),
        FileItemModel(
            id = "2",
            parentId = "1",
            name = "CV_Chadi.pdf",
            isDirectory = false,
            date = "09/09/2026",
            size = 245_760,
            contentType = "application/pdf"
        ),
        FileItemModel(
            id = "3",
            parentId = "1",
            name = "Images",
            isDirectory = true,
            date = "08/09/2026"
        ),
        FileItemModel(
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