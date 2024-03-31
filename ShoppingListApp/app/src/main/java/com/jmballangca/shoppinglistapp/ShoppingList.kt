package com.jmballangca.shoppinglistapp


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.util.Date


data class ShoppingList(val id : Int, val name : String, val quantity : Int,val createdAt : Date,val updatedAt : Date ? = null) {
}

val DEFAULT_LIST = listOf(
    ShoppingList(id=1, name="Milk", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=2, name="Eggs", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=3, name="Bread", quantity=3, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=4, name="Apples", quantity=5, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=5, name="Bananas", quantity=4, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=6, name="Potatoes", quantity=6, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=7, name="Onions", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=8, name="Chicken", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=9, name="Rice", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=10, name="Pasta", quantity=3, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=11, name="Tomatoes", quantity=4, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=12, name="Cheese", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=13, name="Yogurt", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=14, name="Carrots", quantity=3, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=15, name="Spinach", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=16, name="Broccoli", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=17, name="Oranges", quantity=5, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=18, name="Lettuce", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=19, name="Bell Peppers", quantity=3, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=20, name="Ground Beef", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=21, name="Cereal", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=22, name="Salmon", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=23, name="Shrimp", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=24, name="Peanut Butter", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=25, name="Jelly", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=26, name="Crackers", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=27, name="Soda", quantity=3, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=28, name="Juice", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=29, name="Coffee", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=30, name="Tea", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=31, name="Ice Cream", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=32, name="Frozen Pizza", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=33, name="Chocolate", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=34, name="Chips", quantity=3, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=35, name="Cookies", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=36, name="Brownie Mix", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=37, name="Cake Mix", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=38, name="Flour", quantity=2, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=39, name="Sugar", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=40, name="Salt", quantity=1, createdAt=Date(1672742400000), updatedAt=null),
    ShoppingList(id=41, name="Pepper", quantity=1, createdAt=Date(1672742400000), updatedAt=null)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var name by remember {
        mutableStateOf("")
    }
    var quantity by remember {
        mutableStateOf("1")
    }
    val shoppingList = remember { mutableStateListOf<ShoppingList>()}
    shoppingList.addAll(DEFAULT_LIST)
    var editingIndex by remember {
        mutableIntStateOf(-1)
    }
    var filterItem by remember {
        mutableStateOf(false)
    }
    val lazyListState = rememberLazyListState()
    Scaffold(
        topBar = {
                 TopAppBar(title = { Text(text = "Shopping List App")} , actions = {
                     Box {
                         IconButton(onClick = {
                             filterItem = true
                         }) {
                             Image(
                                 painter = painterResource(id = R.drawable.ic_filter),
                                 contentDescription = "Custom Icon",
                                 modifier = Modifier.size(24.dp) ,
                             )}
                         DropdownMenu(expanded = filterItem,
                             onDismissRequest = { filterItem = false}) {
                             DropdownMenuItem(text = { Text(text = "By Name") }, onClick = {
                                 shoppingList.sortBy { it.name }
                                 filterItem =false
                             })
                             DropdownMenuItem(text = { Text(text = "By Quantity") },
                                 onClick = {
                                     shoppingList.sortBy { it.quantity }
                                     filterItem =false
                                 })
                             DropdownMenuItem(text = { Text(text = "By Date") }, onClick = {
                                 shoppingList.sortByDescending {it.updatedAt ?: it.createdAt }
                                 filterItem =false
                             })
                         }
                     }

                 })
        },
        floatingActionButton = {
            if (!lazyListState.isScrollInProgress) {
                FloatingActionButton(onClick = { showBottomSheet = true }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Cart")
                }
            }

        }
    ) {
        LazyColumn(modifier = Modifier.padding(it), state = lazyListState) {
            itemsIndexed(shoppingList,key ={it ,item -> item.id} ) {index ,item ->

                ShoppingItemCard(
                    shoppingList = item,
                    isEditing = index == editingIndex,
                    onEdit = {
                        editingIndex = if (editingIndex == index) {
                            -1
                        }  else {
                            index
                        }
                    },
                    onDelete = { shoppingList.removeAt(index) },
                    onCompleteEdit = { name, qty ->
                        if (index >= 0 && index < shoppingList.size) {
                            shoppingList[index] = shoppingList[index].copy(
                                name = name,
                                quantity = qty,
                                updatedAt = Date()
                            )
                            editingIndex = -1
                        }

                    }
                )
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(onDismissRequest = { showBottomSheet = false }, sheetState = sheetState) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                horizontalAlignment =Alignment.CenterHorizontally)

            {
                Text(text = "Add Shopping List", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))
                TextField(value = name, onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    singleLine = true,
                    label = { Text(text = "Enter name")}
                )
                TextField(value = quantity, 
                    onValueChange = { quantity = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "Enter quantity")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp))
                
                Row(horizontalArrangement = Arrangement.End , modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {
                    TextButton(onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text(text = "Cancel", color = Color.Red)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        if (name.isEmpty() || quantity.isEmpty()) {
                            return@Button
                        }
                        val  item = ShoppingList(id = shoppingList.size + 1,name = name,quantity = quantity.toIntOrNull() ?: 1, createdAt =Date())
                        shoppingList.add(item).also {
                            name = ""
                            quantity = "1"
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        }
                    }, shape = RoundedCornerShape(5.dp)) {
                        Text(text = "Save")
                    }
                }
            }
        }

    }
}

@Composable
fun ShoppingItemCard(shoppingList: ShoppingList,isEditing : Boolean, onEdit : () -> Unit ,onDelete : () -> Unit,onCompleteEdit : (name : String,qty : Int) -> Unit) {
    var inputName by remember {
        mutableStateOf(shoppingList.name)
    }
    var inputQty by remember {
        mutableStateOf(shoppingList.quantity.toString())
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.padding(16.dp)) {
                Column(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()) {
                    Text(text = "${shoppingList.quantity}x ${shoppingList.name}", style = MaterialTheme.typography.titleMedium)

                    Text(
                        text = shoppingList.createdAt.formatDateTime(),
                        style = MaterialTheme.typography.labelSmall,

                    )

                }
                FilledTonalIconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit Icon",tint =MaterialTheme.colorScheme.primary)
                }
                Spacer(modifier = Modifier.width(8.dp))
                FilledTonalIconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Edit Icon", tint = MaterialTheme.colorScheme.error)
                }
            }
            if (isEditing) {
                Column(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.onSecondary)
                        .padding(8.dp)
                ) {
                    Text(text = "${shoppingList.name}", color =  MaterialTheme.colorScheme.secondary, modifier = Modifier.padding(8.dp))
                    Row {
                        TextField(value = inputName, onValueChange = {inputName = it}, label = { Text(
                            text = "Enter name"
                        )} ,modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth())
                        Spacer(modifier = Modifier.width(6.dp))
                        TextField(value = inputQty, onValueChange = {inputQty = it}, label = { Text(
                            text = "Enter Quantity"
                        )}, modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    FilledTonalButton(onClick = {
                        onCompleteEdit(
                            inputName,
                            inputQty.toIntOrNull() ?: 0
                        )
                    },
                        shape = RoundedCornerShape(5.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Save Edit")
                    }
                }
            }
        }

    }
}