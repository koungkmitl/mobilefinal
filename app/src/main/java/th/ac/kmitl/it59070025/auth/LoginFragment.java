package th.ac.kmitl.it59070025.auth;


import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import th.ac.kmitl.it59070025.R;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onClickLogin();
        onClickRegister();
    }

    public void checkState() {
        SharedPreferences sp = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        String state = sp.getString("username", null);

        // redirect
    }

    public void onClickRegister() {
        TextView btnRegister = getView().findViewById(R.id.login_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    public void onClickLogin() {
        Button btnLogin = getView().findViewById(R.id.login_btn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText username = getView().findViewById(R.id.login_username);
                EditText password = getView().findViewById(R.id.login_password);

                String stringUsername = username.getText().toString();
                String stringPassword = password.getText().toString();

                if (stringPassword.isEmpty() && stringUsername.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill out this form", Toast.LENGTH_SHORT).show();
                } else {
                    // not empty
                    SQLiteDatabase sqLiteDatabase =  getActivity().openOrCreateDatabase("my.db", MODE_PRIVATE, null);
                    String sql = String.format("SELECT username, password FROM user where password='%s'", stringPassword);
                    Cursor cs = sqLiteDatabase.rawQuery(sql, null);

                    if(cs.moveToNext()) {
                        String uname = cs.getString(0);
                        String passwd = cs.getString(1);
                        //query success
                        Log.d("login", uname);
                        SharedPreferences sp = getActivity().getSharedPreferences("login", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("username", uname).apply();
                        editor.commit();
                        Toast.makeText(getActivity(), "Login success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Invalid user or password", Toast.LENGTH_SHORT).show();
                    }

                    cs.close();
                }
            }
        });
    }

}
