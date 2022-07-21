package com.example.cryptography;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Vigenere_Cipher extends AppCompatActivity {
    private String messages;
    private String ke;
    Button en,de;
    EditText message,keys;
    TextView result;
    ClipboardManager cpb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vigenere_cipher);
        en=findViewById(R.id.encryption_vigenere_cipher);
        de=findViewById(R.id.decryption_vigenere_cipher);
        cpb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        message=findViewById(R.id.text_vigenere_cipher);
        keys=findViewById(R.id.key_vigenere_cipher);
        result=findViewById(R.id.result_vigenere_cipher);
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get text from edittext
                messages=String.valueOf(message.getText());
                ke=String.valueOf(keys.getText());
                if (ke.length() == 0) {
                    Toast.makeText(v.getContext(), "Enter a key to Encrypt", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (char i : messages.toUpperCase().toCharArray()) {
                    if (i < 'A' || i > 'Z') {
                        Toast.makeText(v.getContext(), "Only Letters are allowed here", Toast.LENGTH_SHORT).show();
                        return;
                    } }
                for (char i : ke.toUpperCase().toCharArray()) {
                    if (i < 'A' || i > 'Z') {
                        Toast.makeText(v.getContext(), "Only Letters are allowed here", Toast.LENGTH_SHORT).show();
                        return;
                    } }

                 Vigenere vv = new Vigenere();
                result.setText(vv.Vigenereencrypt(messages, ke));
                Toast.makeText(v.getContext(), "Encrypt !", Toast.LENGTH_SHORT).show();
            } });
        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messages = String.valueOf(message.getText());
                ke = String.valueOf(keys.getText());
                if (ke.length() == 0) {
                    Toast.makeText(v.getContext(), "Enter a key to Decrypt", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (char i : messages.toUpperCase().toCharArray()) {
                    if (i < 'A' || i > 'Z') {
                        Toast.makeText(v.getContext(), "Only Letters are allowed here", Toast.LENGTH_SHORT).show();
                        return;
                    } }
                for (char i : ke.toUpperCase().toCharArray()) {
                    if (i < 'A' || i > 'Z') {
                        Toast.makeText(v.getContext(), "Only Letters are allowed here", Toast.LENGTH_SHORT).show();
                        return;
                    } }
                Vigenere vv = new Vigenere();
                result.setText(vv.Vigeneredecrypt(messages, ke));
                Toast.makeText(v.getContext(), "Decrypt !", Toast.LENGTH_SHORT).show();
            } });
    }
    public class Vigenere {
        public String Vigenereencrypt (String text, String key)
        {
            String res = "";
            text = text.toUpperCase();
            key = key.toUpperCase();
            for (int i = 0, j = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (c < 'A' || c > 'Z') continue;
                res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
                j = ++j % key.length();
            }
            return res;
        }
        public String Vigeneredecrypt (String text, String key)
        {
            String res = "";
            text = text.toUpperCase();
            key = key.toUpperCase();
            for (int i = 0, j = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (c < 'A' || c > 'Z') continue;
                res += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
                j = ++j % key.length();
            }
            return res;  }
    }

    public void cp_vigenere_cipher(View view) {
        // get the string from the textview and trim all spaces
        String data = result.getText().toString().trim();
        // check if the textview is not empty
         if (!data.isEmpty()) {
             // copy the text in the clip board
           ClipData temp = ClipData.newPlainText("text", data);
            cpb.setPrimaryClip(temp);
             // display message that the text has been copied
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
        }  }
    public  void reset_vigenere_cipher(View view){

        message.setText("");
        keys.setText("");
        result.setText("");

        if(view!=null)
            Toast.makeText(view.getContext(), "All data has been deleted", Toast.LENGTH_SHORT).show();
    } }