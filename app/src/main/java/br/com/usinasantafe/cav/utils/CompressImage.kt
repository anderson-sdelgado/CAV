package br.com.usinasantafe.cav.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.exifinterface.media.ExifInterface
import java.io.File
import java.io.FileOutputStream
import androidx.core.graphics.scale

fun compressImage(
    context: Context,
    imageFile: File
): File {

    val options = BitmapFactory.Options().apply {
        inPreferredConfig = Bitmap.Config.ARGB_8888
    }

    val bitmap = BitmapFactory.decodeFile(
        imageFile.absolutePath,
        options
    )

    val rotatedBitmap = rotateBitmapIfNeeded(
        bitmap,
        imageFile.absolutePath
    )

    val width = rotatedBitmap.width
    val height = rotatedBitmap.height

    val maxSize = 1280

    val ratio = minOf(
        maxSize.toFloat() / width,
        maxSize.toFloat() / height,
        1f
    )

    val resized = rotatedBitmap.scale((width * ratio).toInt(), (height * ratio).toInt())

    val compressedFile = File(
        context.cacheDir,
        "cmp_${imageFile.name}"
    )

    FileOutputStream(compressedFile).use {
        resized.compress(
            Bitmap.CompressFormat.JPEG,
            75,
            it
        )
    }

    bitmap.recycle()

    if (rotatedBitmap != bitmap) {
        rotatedBitmap.recycle()
    }

    resized.recycle()

    return compressedFile
}

fun rotateBitmapIfNeeded(bitmap: Bitmap, imagePath: String): Bitmap {

    val exif = ExifInterface(imagePath)

    val orientation = exif.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_NORMAL
    )

    val matrix = Matrix()

    when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 ->
            matrix.postRotate(90f)

        ExifInterface.ORIENTATION_ROTATE_180 ->
            matrix.postRotate(180f)

        ExifInterface.ORIENTATION_ROTATE_270 ->
            matrix.postRotate(270f)

        else ->
            return bitmap
    }

    return Bitmap.createBitmap(
        bitmap,
        0,
        0,
        bitmap.width,
        bitmap.height,
        matrix,
        true
    )
}