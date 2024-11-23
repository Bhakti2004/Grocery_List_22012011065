package com.example.grocery_list_22012011065

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var itemListContainer: LinearLayout
    private lateinit var totalCostTextView: TextView
    private var totalCost: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemListContainer = findViewById(R.id.itemListContainer)
        totalCostTextView = findViewById(R.id.totalCostTextView)

        // Set up the 'Add Item' button to open the dialog
        val addItemButton: Button = findViewById(R.id.addItemButton)
        addItemButton.setOnClickListener {
            showAddItemDialog()
        }
    }

    // Function to show the dialog to add a new item
    private fun showAddItemDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_item, null)

        val itemNameEditText: EditText = dialogView.findViewById(R.id.itemNameEditText)
        val itemQuantityEditText: EditText = dialogView.findViewById(R.id.itemQuantityEditText)
        val itemPriceEditText: EditText = dialogView.findViewById(R.id.itemPriceEditText)
        val addButton: Button = dialogView.findViewById(R.id.addButton)
        val cancelButton: Button = dialogView.findViewById(R.id.cancelButton)

        builder.setView(dialogView)

        // Create the dialog instance
        val dialog = builder.create()

        // Set up the 'Add' button behavior
        addButton.setOnClickListener {
            val itemName = itemNameEditText.text.toString()
            val quantity = itemQuantityEditText.text.toString().toIntOrNull()
            val price = itemPriceEditText.text.toString().toDoubleOrNull()

            if (itemName.isNotEmpty() && quantity != null && quantity > 0 && price != null && price > 0) {
                addItemToList(itemName, quantity, price)
                dialog.dismiss() // Close the dialog after adding the item
            } else {
                Toast.makeText(this, "Please enter valid details", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up the 'Cancel' button behavior
        cancelButton.setOnClickListener {
            dialog.dismiss() // Close the dialog without adding the item
        }

        dialog.show() // Show the dialog
    }

    // Function to add the item to the list and update the total cost
    private fun addItemToList(name: String, quantity: Int, price: Double) {
        val itemLayout = LayoutInflater.from(this).inflate(R.layout.item_layout, itemListContainer, false)
        val itemNameTextView: TextView = itemLayout.findViewById(R.id.itemNameTextView)
        val itemQuantityTextView: TextView = itemLayout.findViewById(R.id.itemQuantityTextView)
        val itemCostTextView: TextView = itemLayout.findViewById(R.id.itemCostTextView)
        val removeButton: Button = itemLayout.findViewById(R.id.removeButton)

        // Set the item details
        itemNameTextView.text = name
        itemQuantityTextView.text = "Quantity: $quantity"
        itemCostTextView.text = "Price: ₹${price * quantity}"

        // Add the item to the layout
        itemListContainer.addView(itemLayout)

        // Update total cost
        totalCost += price * quantity
        totalCostTextView.text = "Total Cost: ₹$totalCost"

        // Set up the remove button functionality
        removeButton.setOnClickListener {
            itemListContainer.removeView(itemLayout)
            totalCost -= price * quantity
            totalCostTextView.text = "Total Cost: ₹$totalCost"
            Toast.makeText(this, "$name removed", Toast.LENGTH_SHORT).show()
        }
    }
}
