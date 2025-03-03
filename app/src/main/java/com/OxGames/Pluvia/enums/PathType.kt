package com.OxGames.Pluvia.path

import android.content.Context
import com.OxGames.Pluvia.service.SteamService
import com.winlator.xenvironment.ImageFs
import java.nio.file.Paths
import timber.log.Timber

// Refactored to a sealed class so we can support a Raw variant that carries a custom path.
sealed class PathType {

    abstract fun toAbsPath(context: Context, appId: Int): String
    abstract val isWindows: Boolean

    object GameInstall : PathType() {
        override fun toAbsPath(context: Context, appId: Int): String {
            val path = SteamService.getAppDirPath(appId)
            return if (!path.endsWith("/")) "$path/" else path
        }
        override val isWindows: Boolean = true
    }

    object WinMyDocuments : PathType() {
        override fun toAbsPath(context: Context, appId: Int): String {
            val path = Paths.get(
                ImageFs.find(context).rootDir.absolutePath,
                ImageFs.WINEPREFIX,
                "/drive_c/users/",
                ImageFs.USER,
                "Documents/"
            ).toString()
            return if (!path.endsWith("/")) "$path/" else path
        }
        override val isWindows: Boolean = true
    }

    object WinAppDataLocal : PathType() {
        override fun toAbsPath(context: Context, appId: Int): String {
            val path = Paths.get(
                ImageFs.find(context).rootDir.absolutePath,
                ImageFs.WINEPREFIX,
                "/drive_c/users/",
                ImageFs.USER,
                "AppData/Local/"
            ).toString()
            return if (!path.endsWith("/")) "$path/" else path
        }
        override val isWindows: Boolean = true
    }

    object WinAppDataLocalLow : PathType() {
        override fun toAbsPath(context: Context, appId: Int): String {
            val path = Paths.get(
                ImageFs.find(context).rootDir.absolutePath,
                ImageFs.WINEPREFIX,
                "/drive_c/users/",
                ImageFs.USER,
                "AppData/LocalLow/"
            ).toString()
            return if (!path.endsWith("/")) "$path/" else path
        }
        override val isWindows: Boolean = true
    }

    object WinAppDataRoaming : PathType() {
        override fun toAbsPath(context: Context, appId: Int): String {
            val path = Paths.get(
                ImageFs.find(context).rootDir.absolutePath,
                ImageFs.WINEPREFIX,
                "/drive_c/users/",
                ImageFs.USER,
                "AppData/Roaming/"
            ).toString()
            return if (!path.endsWith("/")) "$path/" else path
        }
        override val isWindows: Boolean = true
    }

    object WinSavedGames : PathType() {
        override fun toAbsPath(context: Context, appId: Int): String {
            val path = Paths.get(
                ImageFs.find(context).rootDir.absolutePath,
                ImageFs.WINEPREFIX,
                "/drive_c/users/",
                ImageFs.USER,
                "Saved Games/"
            ).toString()
            return if (!path.endsWith("/")) "$path/" else path
        }
        override val isWindows: Boolean = true
    }

    // Add Linux and Mac implementations as needed…

    // Fallback for unrecognized types. (Could be used for logging or error recovery.)
    object None : PathType() {
        override fun toAbsPath(context: Context, appId: Int): String {
            Timber.e("Did not recognize or unsupported path type, falling back to SteamService path")
            val path = SteamService.getAppDirPath(appId)
            return if (!path.endsWith("/")) "$path/" else path
        }
        override val isWindows: Boolean = true
    }

    // NEW: Raw holds a literal path that should be used as-is.
    data class Raw(val rawPath: String) : PathType() {
        override fun toAbsPath(context: Context, appId: Int): String {
            return if (rawPath.endsWith("/")) rawPath else "$rawPath/"
        }
        // A simple heuristic: if it contains a backslash, we consider it Windows.
        override val isWindows: Boolean = rawPath.contains("\\")
    }

    companion object {
        fun from(keyValue: String?): PathType {
            if (keyValue == null) return None

            val lower = keyValue.lowercase()
            // Extended logic: if no placeholder markers are present, treat it as a raw path.
            if (!lower.contains("%")) {
                return Raw(keyValue)
            }
            return when (lower) {
                "%gameinstall%", "gameinstall" -> GameInstall
                "%winmydocuments%", "winmydocuments" -> WinMyDocuments
                "%winappdatalocal%", "winappdatalocal" -> WinAppDataLocal
                "%winappdatalowlow%", "winappdatalowlow" -> WinAppDataLocalLow
                "%winappdataroaming%", "winappdataroaming" -> WinAppDataRoaming
                "%winsavedgames%", "winsavedgames" -> WinSavedGames
                // Extend with additional cases as needed…
                else -> {
                    Timber.w("Could not identify $keyValue as PathType, treating as raw path")
                    Raw(keyValue)
                }
            }
        }
    }
}
