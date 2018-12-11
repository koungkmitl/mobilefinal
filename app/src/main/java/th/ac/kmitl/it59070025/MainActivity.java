package th.ac.kmitl.it59070025;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import th.ac.kmitl.it59070025.auth.LoginFragment;

public class MainActivity extends AppCompatActivity {

    private static final String SQL_CREATE_DATABASE = "CREATE TABLE IF NOT EXISTS user" +
            "(username VARCHAR(255) PRIMARY KEY," +
            "name VARCHAR(255)," +
            "age VARCHAR(255)," +
            "password VARCHAR(255))";

    private void createDatabase() {
        SQLiteDatabase database = openOrCreateDatabase("my.db", MODE_PRIVATE, null);
        database.execSQL(SQL_CREATE_DATABASE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new LoginFragment())
                    .commit()
            ;
        }
    }
}
