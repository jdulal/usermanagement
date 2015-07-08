package np.com.jdulal.usermanagement;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    DatabaseHelper DbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper=new DatabaseHelper(this);
       // DbHelper.dropUserTable();
        DbHelper.createUserTable();
    }

    public void onButtonClick(View v)
    {
        if(v.getId()==R.id.btnLogin)
        {
            EditText a=(EditText)findViewById(R.id.txtUsername);
            String username=a.getText().toString();

            EditText b=(EditText)findViewById(R.id.txtPassword);
            String pass=b.getText().toString();


            String password= DbHelper.userlogin(username);
            if(pass.equals(password)) {
                Intent i = new Intent(this, UserLoginActivity.class);
                i.putExtra("Username", username);
                i.putExtra("Password", password);
                startActivity(i);
            }
            else
            {
                Toast temp=Toast.makeText(this,"Invalid Login!",Toast.LENGTH_SHORT);
                temp.show();
            }
        }
        if(v.getId()==R.id.btnSignUp)
        {
        Intent i =new Intent(this, SignUpActivity.class);
            startActivity(i);
        }
        if(v.getId()==R.id.btnUserList)
        {
            Intent i=new Intent(this, UserListActivity.class);
            startActivity(i);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
