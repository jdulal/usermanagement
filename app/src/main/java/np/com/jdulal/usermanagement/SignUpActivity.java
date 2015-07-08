package np.com.jdulal.usermanagement;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends ActionBarActivity {
    DatabaseHelper dhelper=new DatabaseHelper(this);
    EditText fullname, address, phone, email,username, password, confpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }
    public void btnCancelClick(View v)
    {
        if(v.getId()==R.id.btnCancel)
        {
            Intent i =new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

    public void onClickNewSignUp(View v)
    {
        if(v.getId()==R.id.btnNewSignUp)
        {
            fullname=(EditText)findViewById(R.id.txtFullname);
            address=(EditText)findViewById(R.id.txtAddress);
            phone=(EditText)findViewById(R.id.txtPhone);
            email=(EditText)findViewById(R.id.txtEmail);
            username=(EditText)findViewById(R.id.txtUsername);
            password=(EditText)findViewById(R.id.txtPassword);
            confpassword=(EditText)findViewById(R.id.txtPassword2);

            String strfullname=fullname.getText().toString();
            String straddress=address.getText().toString();
            String strphone=phone.getText().toString();
            String stremail=email.getText().toString();
            String strusername=username.getText().toString();
            String strpassword=password.getText().toString();
            String strconfpass=confpassword.getText().toString();

            if(!strpassword.equals(strconfpass))
            {
               // Toast pass;
              Toast pass =  Toast.makeText(SignUpActivity.this,"Password did not Match!", Toast.LENGTH_SHORT);
              pass.show();
            }else{

                Contact c=new Contact();
                c.setFullname(strfullname);
                c.setAddress(straddress);
                c.setEmail(stremail);
                c.setPhone(strphone);
                c.setUsername(strusername);
                c.setPassword(strpassword);
                dhelper.AddNewContact(c);

            }

        }
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
