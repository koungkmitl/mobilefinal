package th.ac.kmitl.it59070025.auth;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import th.ac.kmitl.it59070025.R;

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

                if (validated) {
                    // inject to sqlite
                } else {
                    // not register and toast
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

        if (stringUsername.length() >= 6 && stringUsername.length() <= 12) {
            if (stringName.matches("^\\s*$")) {
                try {
                    int intAge = Integer.parseInt(stringAge);
                    if (intAge >= 10 && intAge <= 80) {
                        if (stringPassword.length() > 6) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;

    }
}
