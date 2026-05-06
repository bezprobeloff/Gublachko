package com.example.gublachko.utils

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import com.example.gublachko.data.models.AppInfo


fun getInstalledApps(context: Context): List<AppInfo> {
    val packageManager = context.packageManager
    val packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

//    return packages.mapNotNull { pkg ->
//        val name = packageManager.getApplicationLabel(pkg).toString()
//        val icon = pkg.loadIcon(packageManager)
//        if (pkg.packageName == context.packageName) null
//        else AppInfo(name, pkg.packageName, icon)
//    }.sortedBy { it.name }

    return packages.mapNotNull { pkg ->
        // Проверяем, есть ли у пакета LAUNCHER activity
        val launchIntent = packageManager.getLaunchIntentForPackage(pkg.packageName)
        if (launchIntent != null) {
            val name = packageManager.getApplicationLabel(pkg).toString()
            val icon = pkg.loadIcon(packageManager)
            // Исключаем само приложение-лаунчер (чтобы не зацикливаться)
            if (pkg.packageName == context.packageName) null
            else AppInfo(name, pkg.packageName, icon)
        } else {
            null // нет иконки запуска — пропускаем
        }
    }.sortedBy { it.name }

}

fun launchApp(context: Context, packageName: String) {
    try {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Приложение не найдено: $packageName", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        Toast.makeText(context, "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}