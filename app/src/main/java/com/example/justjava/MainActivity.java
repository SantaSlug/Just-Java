package com.example.justjava;

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/


import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;  //quantity is a global variable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
//        if (quantity == 100){
//            return;   // we can use this code as well
//        }
        quantity = quantity + 1;
        if (quantity > 100){
            quantity = 100 ;
            Toast.makeText(this,"You cannot have more than 100 cups of coffee",Toast.LENGTH_SHORT).show();

        }
        display(quantity);
      //  displayPrice(quantity * 5);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedCreamCheckBox =  findViewById(R.id.whipped_cream_checkbox); // in newer version of android studio type casting
        boolean hasWippedCream = whippedCreamCheckBox.isChecked();                // isn't required

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameEditText = (EditText) findViewById(R.id.name_field);
        String customerName = nameEditText.getText().toString();

      //  display(quantity);
     //   displayPrice(quantity * 5);
        int price = calculatePrice(hasWippedCream , hasChocolate);
        String priceMessage = createOrderSummary(price , hasWippedCream ,hasChocolate , customerName);
        displayMessage(priceMessage);

    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
//        if (quantity == 1){
//            return;   // we can use this code as well
//        }
        quantity = quantity - 1;
        if (quantity < 1){
            quantity = 1;
            Toast.makeText(this,"You cannot have less than 1 cup of coffee",Toast.LENGTH_SHORT).show();
        }
        display(quantity);
     //   displayPrice(quantity * 5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
    /**
     * Calculates the price of the order.
     *
     * @return totalPrice
     */
    private int calculatePrice(boolean hasWippedCream , boolean hasChocolaate) {
        int pricePerCup = 5;
        if(hasChocolaate == true){
            pricePerCup += 2;
        }
        if (hasWippedCream == true){
            pricePerCup += 1;
        }
        int totalPrice = quantity * pricePerCup;
        return totalPrice;
    }

    private String createOrderSummary(int Price , boolean addWhippedCream , boolean addChocolate ,String custName){
        String priceMessage = "Name: " + custName;
        priceMessage += "\nAdd Whipped Cream? " + addWhippedCream ;
        priceMessage += "\nAdd Chocolate? " + addChocolate ;
        priceMessage = priceMessage + " \nQuantity: " + quantity;
        priceMessage = priceMessage +" \nTotal: $" +Price +"\nThank You!" ;
        return  priceMessage;
    }
}