package th.ac.kmitl.it59070025.auth;

import android.content.ContentValues;
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
import android.widget.Toast;

import th.ac.kmitl.it59070025.R;
import static android.content.Context.MODE_PRIVATE;

public class RegisterFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onClickRegister();
    }

    public void onClickRegister() {
        Button btn = getView().findViewById(R.id.register_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validated = formValidate();

                EditText username = getView().findViewById(R.id.register_username);
                EditText name = getView().findViewById(R.id.register_name);
                EditText age = getView().findViewById(R.id.register_age);
                EditText password = getView().findViewById(R.id.register_password);

                String stringUsername = username.getText().toString();
                String stringName = name.getText().toString();
                String stringAge = age.getText().toString();
                String stringPassword = password.getText().toString();

                if (validated) {
                    // inject to sqlite
                    Log.d("login", "validate passed");
                    ContentValues cv = new ContentValues();
                    cv.put("username", stringUsername);
                    cv.put("name", stringName);
                    cv.put("age", stringAge);
                    cv.put("password", stringPassword);

                    SQLiteDatabase sqLiteDatabase =  getActivity().openOrCreateDatabase("my.db", MODE_PRIVATE, null);

                    sqLiteDatabase.insert("user", null, cv);

                    sqLiteDatabase.close();
                    cv.clear();

                    Toast.makeText(getActivity(), "registed", Toast.LENGTH_SHORT).show();
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoginFragment())
                            .addToBackStack(null)
                            .commit();
                } else {
                    Log.d("login", "validate failure");
                    Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean formValidate() {
        EditText username = getView().findViewById(R.id.register_username);
        EditText name = getView().findViewById(R.id.register_name);
        EditText age = getView().findViewById(R.id.register_age);
        EditText password = getView().findViewById(R.id.register_password);

        String stringUsername = username.getText().toString();
        String stringName = name.getText().toString();
        String stringAge = age.getText().toString();
        String stringPassword = password.getText().toString();
        try {
            int intAge = Integer.parseInt(stringAge);

            if (stringUsername.length() >= 6 && stringUsername.length() <= 12) {
                if (stringName.contains(" ")) {
                    if (intAge >= 10 && intAge <= 80) {
                        if (stringPassword.length() > 6) {
                            return true;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        } catch (Exception e) {
            return false;
        }



    }
}
