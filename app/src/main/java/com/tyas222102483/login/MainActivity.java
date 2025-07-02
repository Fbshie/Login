package com.tyas222102483.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private Button _loginButton;
    private EditText _idEditText;
    private EditText _passwordEditText;
    private Intent _menuIntent;
    private String _id;
    private String _password;
    private String _url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        _idEditText = (EditText) findViewById(R.id.idEditText);
        _loginButton = (Button) findViewById(R.id.loginButton);
        _passwordEditText = (EditText) findViewById(R.id.passwordEditText);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        _loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
            _id = _idEditText.getText().toString();
            _password = _passwordEditText.getText().toString();
            _url = "https://stmikpontianak.cloud/011100862/login.php?id=" + _id + "&password=" + _password;

                AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

                asyncHttpClient.get(_url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String hasil = new String(responseBody);

                        if (!hasil.equals("[{\"idCount\":\"1\"}]"))
                        {
                            Toast.makeText(getApplicationContext(), "ID dan password anda tidak cocok.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(getApplicationContext(), "Selamat datang, " + _id, Toast.LENGTH_SHORT).show();

                        _menuIntent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(_menuIntent); //munculkan Hal "Menu"

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
                    {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}