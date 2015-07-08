package np.com.jdulal.usermanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

import javax.xml.validation.Validator;

/**
 * Created by jagadish on 7/6/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    static String dbname="jddb";
    static int dbversion=1;
    private static final String TABLE_NAME="tblUser";
    private static final String COLUMN_NAME="fullname";
    private static final String COLUMN_ADDRESS="address";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_PHONE="phone";
    private static final String COLUMN_USERNAME="username";
    private static final String COLUMN_PASSWORD="password";
    //private static final String COLUMN_NAME="fullname";

    //public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
    public DatabaseHelper(Context context) {
        super(context, dbname, null, dbversion);
    }

    public  void createUserTable()
    {
        String sql="CREATE TABLE IF NOT EXISTS `tblUser` (`uid` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`fullname` TEXT, `address` INTEGER, `phone` TEXT, `email` TEXT, `username` TEXT, `password` TEXT);";
        getWritableDatabase().execSQL(sql);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void  AddNewContact(Contact c)
    {

        db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_NAME,c.getFullname());
        values.put(COLUMN_ADDRESS,c.getAddress());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PHONE, c.getPhone());
        values.put(COLUMN_USERNAME, c.getUsername());
        values.put(COLUMN_PASSWORD,c.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();


    }
    public ArrayList<UserInfo> ShowUserList()
    {
        String sql="SELECT * FROM tblUser";
        Cursor c=getWritableDatabase().rawQuery(sql,null);
        ArrayList<UserInfo> infolist=new ArrayList<UserInfo>();
        while (c.moveToNext())
        {
        UserInfo u=new UserInfo();
            u.fullname=c.getString(c.getColumnIndex("fullname"));
            u.address=c.getString(c.getColumnIndex("address"));
            u.email=c.getString(c.getColumnIndex("email"));
            u.phone=c.getString(c.getColumnIndex("phone"));
            infolist.add(u);
        }
        return  infolist;
    }
    public String userlogin(String username)
    {
        db=this.getReadableDatabase();
        String sql="SELECT username,password FROM tblUser WHERE username="+username;
        Cursor cursor=db.rawQuery(sql,null);
        //cursor.moveToNext();
        String a, b;
        b="User not found";
        if(cursor.moveToFirst())
        {
            do{
                a=cursor.getString(0);
                if(a.equals(username))
                {
                    b=cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
            }
        return b;
        }



}
