package com.example.cocktailbar

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object AlertDialogBuilder {

     fun noReadStoragePermission(context: Context): AlertDialog =
        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.photo_picker_no_permission_title_text)
            .setMessage(R.string.photo_picker_no_permission_description_text)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

    /**
     * Диалог с пояснением об отсутствии разрешения на запись файлов
     */
     fun noWriteStoragePermission(context: Context): AlertDialog =
        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.settings_backup_permission_title_text)
            .setMessage(R.string.settings_backup_permission_description_text)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

}