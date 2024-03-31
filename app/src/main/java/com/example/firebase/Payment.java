package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.annotation.SuppressLint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase.credential.MainHome;


public class Payment extends AppCompatActivity {
    Toolbar toolbar;

    Button payment_btn;
    TextView subTotal,shipping,discount,total;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        payment_btn=findViewById(R.id.payment_btn_final);
       discount=findViewById(R.id.payment_discount);
       subTotal=findViewById(R.id.payment_subtotal);
       shipping=findViewById(R.id.payment_shipping);
        total=findViewById(R.id.payment_total);
      //  String quan;
       // quan=getIntent().getStringExtra("quan");

/*       int amount;
//        amount=getIntent().getIntExtra("amount",0);
//
      8?subTotal.setText("$"+amount);
 */

        String amount;

        amount=getIntent().getStringExtra("amount");
      //  String fin=String.valueOf(Integer.parseInt(amount)*Integer.parseInt(quan));
        subTotal.setText(amount);



        int final_shipping=21;
        int final_discount= 0;
        if (amount != null) {
            final_discount = (Integer.parseInt(amount)/2);
        }
        String final_total=String.valueOf(final_shipping+final_discount);
       total.setText(final_total);
       discount.setText("50%");
       shipping.setText(String.valueOf(final_shipping));




        //LocalBroadcastManager.getInstance(this).registerReceiver(receiver,new IntentFilter("myTotalAmount"));
   payment_btn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           startActivity(new Intent(Payment.this, MainHome.class));
           finish();
           Toast.makeText(Payment.this, "Payment Successful....Thanks For Purchasing", Toast.LENGTH_SHORT).show();
       }
   });


    }
/*   private void paymentMethod(int val){
//
//
//        try {
//            JSONObject options = new JSONObject();
//            //Set Company Name
//            options.put("name", "My E-Commerce App");
//            //Ref no
//            options.put("description", "Reference No. #123456");
//            //Image to be display
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
//            //options.put("order_id", "order_9A33XWu170gUtm");
//            // Currency type
//            options.put("currency", "USD");
//            //double total = Double.parseDouble(mAmountText.getText().toString());
//            //multiply with 100 to get exact amount in rupee
//
//            val = val * 100;
//            //amount
//            options.put("amount", amount);
//            JSONObject preFill = new JSONObject();
//            //email
//            preFill.put("email", "developer.kharag@gmail.com");
//            //contact
//            preFill.put("contact", "7489347378");
//
//            options.put("prefill", preFill);
//
//            checkout.open(activity, options);
//        } catch (Exception e) {
//            Log.e("TAG", "Error in starting Razorpay Checkout", e);
//        }
//    }

 */

}