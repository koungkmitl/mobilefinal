package th.ac.kmitl.it59070025.home;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import th.ac.kmitl.it59070025.R;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onClickSave() {
        Button btn = getView().findViewById(R.id.profile_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    // passed

                    EditText username = getView().findViewById(R.id.profile_username);
                    EditText name = getView().findViewById(R.id.profile_name);
                    EditText age = getView().findViewById(R.id.profile_age);
                    EditText password = getView().findViewById(R.id.profile_password);
                    EditText quote = getView().findViewById(R.id.profile_quote);

                    String stringUsername = username.getText().toString();
                    String stringName = name.getText().toString();
                    String stringAge = age.getText().toString();
                    String stringPassword = password.getText().toString();
                    String stringQuote = quote.getText().toString();

                    SQLiteDatabase sqLiteDatabase =  getActivity().openOrCreateDatabase("my.db", MODE_PRIVATE, null);
                    String sql  = String.format("UPDATE user SET name='%s', age='%s', password='%s' where username='%s'", stringName, stringAge, stringPassword, stringUsername);
                    String filename = "quote.txt";

                    sqLiteDatabase.rawQuery(sql, null);

                    sqLiteDatabase.close();

                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new HomeFragment())
                            .commit();
                } else {
                    Toast.makeText(getActivity(), "Somw field was empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validate() {
        EditText username = getView().findViewById(R.id.profile_username);
        EditText name = getView().findViewById(R.id.profile_name);
        EditText age = getView().findViewById(R.id.profile_age);
        EditText password = getView().findViewById(R.id.profile_password);
        EditText quote = getView().findViewById(R.id.profile_quote);

        String stringUsername = username.getText().toString();
        String stringName = name.getText().toString();
        String stringAge = age.getText().toString();
        String stringPassword = password.getText().toString();
        String stringQuote = quote.getText().toString();

        try {
            int intAge = Integer.parseInt(stringAge);
            if (stringUsername.length() >= 6 && stringUsername.length() <= 12) {
                if (stringName.contains(" ")) {
                    if (intAge >= 10 && intAge <= 80) {
                        if (stringPassword.length() >=  6) {
                            if (!stringQuote.isEmpty()) {
                                return true;
                            }
                            return false;
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
