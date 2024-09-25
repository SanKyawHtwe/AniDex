package com.skhkma.anidex.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.skhkma.anidex.designsystem.theme.AniDexTheme

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    text: String,
    onDismissClick: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissClick,
        content = {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column {
                    Text(
                        text = text,
                        modifier = Modifier.padding(16.dp),
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        TextButton(
                            onClick = onDismissClick,
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text("Dismiss")
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun ErrorDialogPreview() {
    AniDexTheme {
        ErrorDialog(
            text = "Error",
            onDismissClick = {},
        )
    }
}
