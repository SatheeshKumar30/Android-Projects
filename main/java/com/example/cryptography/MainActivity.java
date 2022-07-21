package com.example.cryptography;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ramotion.foldingcell.FoldingCell;
public class MainActivity extends AppCompatActivity {
    ImageView m_aes,m_caesarcipher,m_binary,m_vigenere_cipher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_aes=findViewById(R.id.main_aes);
        m_caesarcipher=findViewById(R.id.main_cc);
        m_binary=findViewById(R.id.main_binary);
        m_vigenere_cipher=findViewById(R.id.main_vc);


        m_aes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aes_i= new Intent(getApplicationContext(),AES.class);
                startActivity(aes_i);
            }
        });
        m_caesarcipher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent caesarcipher_i= new Intent(getApplicationContext(),Caesar_Cipher.class);
                startActivity(caesarcipher_i);
            }
        });
        m_binary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent binary_i= new Intent(getApplicationContext(),Binary.class);
                startActivity(binary_i);
            }
        });
        m_vigenere_cipher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vc_i= new Intent(getApplicationContext(),Vigenere_Cipher.class);
                startActivity(vc_i);
            }
        });
    }
}