package com.busycount.common.sample

import android.Manifest
import android.content.ContentUris
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Size
import com.busycount.common.sample.databinding.ActivityStorageBinding
import com.busycount.core.ui.BasicActivity
import com.busycount.core.ui.title.BasicTitleBar
import java.util.concurrent.TimeUnit


/**
 * @author : thalys_ch
 * Date : 2021/07/28
 * Describe :
 **/
class StorageActivity : BasicActivity() {

    private lateinit var binding: ActivityStorageBinding

    override fun initCreateView() {
        binding = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setCustomTitleBar(): BasicTitleBar {
        return TestTitle()
    }

    override fun initLogic() {
        binding.read.setOnClickListener {
            find()
        }



    }


    private fun find() {
        log("-start-")
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE
        )

        val selection = MediaStore.Video.Media.DURATION + " >= ?"
        val selectionArgs = arrayOf(TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES).toString())
        val sortOrder = MediaStore.Video.Media.DISPLAY_NAME + " ASC"

        val cursor = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, sortOrder)

        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val nameColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val durationColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            val sizeColumn = it.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)

            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val name = it.getString(nameColumn)
                val duration = it.getInt(durationColumn)
                val size = it.getInt(sizeColumn)
                val contentUri: Uri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id)

                log("$name ($id): duration $duration ,size $size , uri $contentUri")

            }
        }
    }


    private fun loadThumb(uri: Uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val smallImage: Bitmap = contentResolver.loadThumbnail(uri, Size(640, 480), null)
        }



        contentResolver.openFileDescriptor(uri, "r").use {

        }


//        contentResolver.openInputStream()
    }


    private fun exec11() {
//        MediaStore.createWriteRequest()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION), 1002)

        }
    }

    private fun exec() {
//        cacheDir
//        getExternalFilesDirs()
    }


}