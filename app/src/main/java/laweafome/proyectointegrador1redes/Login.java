package laweafome.proyectointegrador1redes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Comunicacion com;
    EditText user;
    EditText pass;
    Button login;
    Button sing_up;
    Context c;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        com.getInstance();
        c = this;
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        sing_up = (Button) findViewById(R.id.sing_up);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user.getText().toString() != null || user.getText().toString() != "" || user.getText().toString() != "Usuario" ){
                    if(pass.getText().toString() != null || pass.getText().toString() != "" || pass.getText().toString() != "Contraseña" ){
                        String mensaje= "Usuario:"+user.getText().toString()+"Contraseña:"+pass.getText().toString();
                        com.getInstance().enviar(mensaje);
                        Toast.makeText(c,"se envio",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });




    }
}
