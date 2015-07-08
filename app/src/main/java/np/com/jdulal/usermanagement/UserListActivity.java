package np.com.jdulal.usermanagement;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class UserListActivity extends ActionBarActivity {
    DatabaseHelper db;
    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_layout);
        container=(LinearLayout)findViewById(R.id.container);
        db=new DatabaseHelper(this);
        ArrayList<UserInfo> infolist=db.ShowUserList();
        for(int i=0; i<infolist.size(); i++)
        {
            UserInfo item=infolist.get(i);
            TextView tv= new TextView(this);
            tv.setText(item.fullname+" "+item.address);
            container.addView(tv);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_list, menu);
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
