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

public class Caesar_Cipher extends AppCompatActivity {
    private String messages;
    private String ke;
    private Button en, de;
    private EditText message_text, keys;
    private TextView result;
    private  ClipboardManager cpb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar_cipher);
        en = findViewById(R.id.encryption_caesar_cipher);
        de = findViewById(R.id.decryption_caesar_cipher);
        cpb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        message_text = findViewById(R.id.text_caesar_cipher);
        keys = findViewById(R.id.key_caesar_cipher);
        result = findViewById(R.id.result_caesar_cipher);
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get text from edittext
                messages = String.valueOf(message_text.getText());
                ke = String.valueOf(keys.getText());
                try {
                    if (ke.length() == 0) {
                        Toast.makeText(v.getContext(), "Enter a key to Encrypt", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (Integer.parseInt(ke) >= 26) {
                        Toast.makeText(v.getContext(), "The Key must be less than 26 characters", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Caesarcipher c = new Caesarcipher();
                    result.setText(c.caesarcipherEnc(messages, Integer.parseInt(ke)));
                    Toast.makeText(v.getContext(), "Encrypt !", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "Your key should me number", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } }
        });
        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messages = String.valueOf(message_text.getText());
                ke = String.valueOf(keys.getText());
                try {
                    if (ke.length() == 0) {
                        Toast.makeText(v.getContext(), "Enter a key to decrypt", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (Integer.parseInt(ke) >= 26) {
                        Toast.makeText(v.getContext(), "The Key must be less than 26 characters", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Caesarcipher c = new Caesarcipher();
                    result.setText(c.caesarcipherDec(messages, Integer.parseInt(ke)));
                    Toast.makeText(v.getContext(), "Decrypt !", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(v.getContext(), "Your key is wrong", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } } });
    }

    public class Caesarcipher {
        char ch;
        char NumbTest[] = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9'};
        public String caesarcipherEnc (String message,int key){
            String encryptedMessage = "";
            int n = 1;
            for (int i = 0; i < message.length(); i++) {
                n = 1;
                ch = message.charAt(i);
                for (int j = 0; j < NumbTest.length; j++)
                { if (ch == NumbTest[j])
                    {
                        if((char)key+ch>'9')
                            break;
                        ch = (char) (ch + key);
                        encryptedMessage += ch;
                        n = 0;
                        break;
                    } }
                if (n == 0)
                {
                    continue;
                } else
                if (ch >= 'a' && ch <= 'z')
                { ch = (char) (ch + key);
                    if (ch > 'z') {
                        ch = (char) (ch - 'z' + 'a' - 1);
                    }
                    encryptedMessage += ch;
                } else if (ch >= 'A' && ch <= 'Z') {
                    ch = (char) (ch + key);
                    if (ch > 'Z') {
                        ch = (char) (ch - 'Z' + 'A' - 1);
                    }
                    encryptedMessage += ch;
                } else
                    encryptedMessage += ch;
            }
            return encryptedMessage;
        }
        public String caesarcipherDec (String message,int key)
        {
            String decryptedMessage = "";
            int n = 1;
            for (int i = 0; i < message.length(); i++) {
                n = 1;
                ch = message.charAt(i);
                for (int j = 0; j < NumbTest.length; j++) {
                    if (ch == NumbTest[j]) {

                        if((char)key+ch>'9')
                            break;
                        ch = (char) (ch - key);
                        decryptedMessage += ch;
                        n = 0;
                        break;
                    }
                }
                if (n == 0)
                {
                    continue;
                } else if (ch >= 'a' && ch <= 'z') {
                    ch = (char) (ch - key);
                    if (ch < 'a') {
                        ch = (char) (ch + 'z' - 'a' + 1);
                    }
                    decryptedMessage += ch;
                } else if (ch >= 'A' && ch <= 'Z') {
                    ch = (char) (ch - key);
                    if (ch < 'A') {
                        ch = (char) (ch + 'Z' - 'A' + 1);
                    }
                    decryptedMessage += ch;
                } else
                    decryptedMessage += ch;
            }
            return decryptedMessage;
        } }
    public void cp_caesar_cipher(View view) {
        // get the string from the textview and trim all spaces
        String data = result.getText().toString().trim();
        // check if the textview is not empty
        if (!data.isEmpty()) {
            // copy the text in the clip board
            ClipData temp = ClipData.newPlainText("text", data);
            cpb.setPrimaryClip(temp);
            // display message that the text has been copied
            Toast.makeText(view.getContext(), "Copied", Toast.LENGTH_SHORT).show();
        }  }

    public void reset_caesar_cipher(View view) {
        message_text.setText("");
        keys.setText("");
        result.setText("");
        if (view != null)
            Toast.makeText(view.getContext(), "All data has been deleted", Toast.LENGTH_SHORT).show();
    } }