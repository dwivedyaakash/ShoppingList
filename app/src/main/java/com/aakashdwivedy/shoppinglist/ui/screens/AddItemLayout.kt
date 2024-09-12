package com.aakashdwivedy.shoppinglist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aakashdwivedy.shoppinglist.data.ShoppingItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemLayout(
    shoppingListSize: Int,
    itemToEdit: ShoppingItem?,
    updateShoppingList: (ShoppingItem) -> Unit,
    hideAddItemLayout: () -> Unit
) {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    val dialogTitle: String
    val dialogAction: String
    val nameLabel: String
    val quantityLabel: String

    if (itemToEdit != null) {
        dialogTitle = "Edit item"
        dialogAction = "Save"
        nameLabel = "Item name"
        quantityLabel = "Item quantity"
        itemName = itemToEdit.name
        itemQuantity = itemToEdit.quantity.toString()
    } else {
        dialogTitle = "Add item"
        dialogAction = "Add"
        nameLabel = "Enter item name"
        quantityLabel = "Enter item quantity"
    }

    BasicAlertDialog(
        modifier = Modifier.background(
            Color.DarkGray,
            shape = RoundedCornerShape(16.dp)
        ),
        onDismissRequest = { hideAddItemLayout() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = dialogTitle, color = Color.White)
            OutlinedTextField(
                value = itemName,
                onValueChange = { itemName = it },
                label = { Text(text = nameLabel, color = Color.LightGray) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            OutlinedTextField(
                value = itemQuantity,
                onValueChange = { itemQuantity = it },
                label = { Text(text = quantityLabel, color = Color.LightGray) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        if (itemName.isNotEmpty() && itemQuantity.isNotEmpty()) {
                            val newItem = ShoppingItem(
                                id = itemToEdit?.id ?: (shoppingListSize + 1),
                                name = itemName,
                                quantity = itemQuantity.toInt()
                            )
                            updateShoppingList(newItem)
                        }
                        hideAddItemLayout()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = dialogAction)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { hideAddItemLayout() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Cancel")
                }
            }
        }
    }
}
