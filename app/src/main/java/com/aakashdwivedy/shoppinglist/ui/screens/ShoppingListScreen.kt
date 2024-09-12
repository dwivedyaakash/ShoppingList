package com.aakashdwivedy.shoppinglist.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aakashdwivedy.shoppinglist.data.ShoppingItem

@Composable
fun ShoppingListScreen(modifier: Modifier = Modifier) {
    var shoppingList by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showAddItemLayout by remember { mutableStateOf(false) }
    var itemToEdit by remember { mutableStateOf(null as ShoppingItem?) }

    Column(
        modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "My Shopping List",
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)
                .background(Color(0xFF202020), shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
        ) {
            LazyColumn(Modifier.fillMaxHeight()) {
                if (shoppingList.isEmpty()){
                    item {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Shopping list is empty!",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.LightGray
                        )
                    }
                }
                items(shoppingList.size) { index ->
                    ShoppingListItem(
                        shoppingList[index],
                        showEditDialog = { item ->
                            itemToEdit = item
                            showAddItemLayout = true
                        },
                        removeItem = { item -> shoppingList = shoppingList - item }
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier,
                onClick = { showAddItemLayout = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Add item")
            }
        }
    }

    if (showAddItemLayout) {
        AddItemLayout(
            shoppingListSize = shoppingList.size,
            itemToEdit = itemToEdit,
            updateShoppingList = { newItem ->
                if (shoppingList.isEmpty()) {
                    shoppingList += newItem
                } else {
                    var updated = false
                    shoppingList.map {
                        if (it.id == newItem.id) {
                            shoppingList -= it
                            shoppingList += newItem
                            updated = true
                        }
                    }
                    if (!updated) {
                        shoppingList += newItem
                    }
                }
            },
            hideAddItemLayout = {
                itemToEdit = null
                showAddItemLayout = false
            }
        )
    }
}
