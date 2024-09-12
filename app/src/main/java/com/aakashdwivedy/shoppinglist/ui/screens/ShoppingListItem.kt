package com.aakashdwivedy.shoppinglist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aakashdwivedy.shoppinglist.R
import com.aakashdwivedy.shoppinglist.data.ShoppingItem

@Composable
fun ShoppingListItem(
    shoppingItem: ShoppingItem,
    showEditDialog: (ShoppingItem) -> Unit,
    removeItem: (ShoppingItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(0.7f)) {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = shoppingItem.name,
                fontSize = 24.sp,
                color = Color.White
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontSize = 14.sp,
                            color = Color.LightGray,
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        append("Quantity: ")
                    }
                    append(shoppingItem.quantity.toString())
                },
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        IconButton(onClick = { showEditDialog(shoppingItem) }) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .weight(0.15f),
                painter = painterResource(R.drawable.ic_edit),
                contentDescription = "edit item",
                tint = Color.White
            )
        }
        IconButton(onClick = { removeItem(shoppingItem) }) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .weight(0.15f),
                painter = painterResource(R.drawable.ic_delete),
                contentDescription = "delete item",
                tint = Color.Red
            )
        }
    }
}
