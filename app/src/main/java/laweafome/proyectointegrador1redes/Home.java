package laweafome.proyectointegrador1redes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button login;
    Button sing_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        login = (Button) findViewById(R.id.login);
        sing_up = (Button) findViewById(R.id.sing_up);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent screen = new Intent(Home.this,Login.class);
                startActivity(screen);
            }
        });

        sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent screen = new Intent(Home.this,Registro.class);
                startActivity(screen);
            }
        });
    }
}
