package com.example.trialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class New_Item_Form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item_form);

        final EditText product_id = (EditText) findViewById(R.id.product_id);
        final EditText product_name = (EditText) findViewById(R.id.product_name);
        final EditText product_category = (EditText) findViewById(R.id.product_category);
        final EditText product_brand = (EditText) findViewById(R.id.product_brand);

        final EditText product_length = (EditText) findViewById(R.id.product_length);
        final EditText product_breadth = (EditText) findViewById(R.id.product_breadth);
        final EditText product_height = (EditText) findViewById(R.id.product_height);
        final EditText product_weight = (EditText) findViewById(R.id.product_weight);

        final EditText product_cost = (EditText) findViewById(R.id.product_cost);
        final EditText product_mrp = (EditText) findViewById(R.id.product_mrp);
        final EditText product_gst = (EditText) findViewById(R.id.product_gst);

        final EditText product_description = (EditText) findViewById(R.id.description_box);

        Button create_product = (Button) findViewById(R.id.create_product);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final DatabaseReference user_db = FirebaseDatabase.getInstance().getReference("Database");
        final DatabaseReference product = FirebaseDatabase.getInstance().getReference("Product");

        create_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String p_id = product_id.getText().toString().trim();
                final String p_name = product_name.getText().toString().trim();
                final String p_category = product_category.getText().toString().trim();
                final String p_brand = product_brand.getText().toString().trim();

                final String p_length = product_length.getText().toString().trim();
                final String p_breadth = product_breadth.getText().toString().trim();
                final String p_height = product_height.getText().toString().trim();
                final String p_weight = product_weight.getText().toString().trim();

                final String p_cost = product_cost.getText().toString().trim();
                final String p_mrp = product_mrp.getText().toString().trim();
                final String p_gst = product_gst.getText().toString().trim();

                final String p_description = product_description.getText().toString().trim();

                String userid = mAuth.getCurrentUser().getUid();
                String product = p_id;
                Map<String, Object> db = new HashMap<>();
                db.put("UID", userid);
                db.put("P_id", p_id);
                db.put("Product Name", p_name);
                db.put("Category", p_category);
                db.put("Brand", p_brand);
                db.put("Length", p_length);
                db.put("Breadth", p_breadth);
                db.put("Height", p_height);
                db.put("Weight", p_weight);
                db.put("Cost", p_cost);
                db.put("MRP", p_mrp);
                db.put("GST", p_gst);
                db.put("Description", p_description);

                user_db.child(userid).child(product).setValue(db);

                startActivity(new Intent(getApplicationContext(), Edit_Database.class));
            }
        });
    }
}