package com.example.composecourse

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Light Mode Preview",
    group = "UI Mode",
    uiMode = UI_MODE_NIGHT_NO or UI_MODE_TYPE_NORMAL,
    showBackground = true,
)
@Preview(
    name = "Night Mode Preview",
    group = "UI Mode",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    showBackground = true,
)
 annotation class LightDarkThemePreview