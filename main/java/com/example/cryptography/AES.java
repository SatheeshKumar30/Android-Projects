package com.example.cryptography;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES extends AppCompatActivity {
    private String messages;
    private String ke;
    Button en,de;
    EditText message,keys;
    TextView result;
    ClipboardManager cpb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes);
        en=findViewById(R.id.encryption_aes);
        de=findViewById(R.id.decryption_aes);
        cpb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        message=findViewById(R.id.text_aes);
        keys=findViewById(R.id.key_aes);
        result=findViewById(R.id.result_ase);
        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get text from edittext
                messages=String.valueOf(message.getText());
                ke=String.valueOf(keys.getText());
                try {
                    // pass the string to the encryption
                    // algorithm and get the encrypted code
                    String a = AESencrypt(ke.getBytes("UTF-16LE"),messages.getBytes("UTF-16LE"));
                    // set the code to the edit text
                    result.setText(a);
                    Toast.makeText(v.getContext(), "Encrypt !", Toast.LENGTH_SHORT).show();
                }catch ( Exception e)
                {
                    Toast.makeText(v.getContext(), "Not Encrypt", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } }
        });
        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get code from edittext
                messages=String.valueOf(message.getText());
                ke=String.valueOf(keys.getText());
                try {
                    // pass the string to the decryption algorithm
                    // and get the decrypted text
                    String decData=AESdecrypt(ke, Base64.decode(messages.getBytes("UTF-16LE"), Base64.DEFAULT));
                    // set the text to the edit text for display
                    result.setText(decData);
                    Toast.makeText(v.getContext(), "Decrypt !", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(v.getContext(), "Your key is wrong", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } }
        });

    }
    public String AESencrypt ( byte[] key, byte[] clear) throws Exception {
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] digestOfPassword = md.digest(key);
        SecretKeySpec skeySpec = new SecretKeySpec(digestOfPassword, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return Base64.encodeToString(encrypted, Base64.DEFAULT);
    }
    public String AESdecrypt (String key,byte[] encrypted) throws Exception {
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] digestOfPassword = md.digest(key.getBytes("UTF-16LE"));
        SecretKeySpec skeySpec = new SecretKeySpec(digestOfPassword, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return new String(decrypted, "UTF-16LE");
    }
    public void cp_aes(View view) {
        // get the string from the textview and trim all spaces
        String data = result.getText().toString().trim();

        // check if the textview is not empty
        if (!data.isEmpty()) {
            // copy the text in the clip board
            ClipData temp = ClipData.newPlainText("text", data);
            cpb.setPrimaryClip(temp);
            // display message that the text has been copied
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show();
        }
    }
    public  void reset_aes(View view){
       message.setText("");
        keys.setText("");
        result.setText("");
        if(view!=null)
            Toast.makeText(view.getContext(), "All data has been deleted", Toast.LENGTH_SHORT).show();
    }
}