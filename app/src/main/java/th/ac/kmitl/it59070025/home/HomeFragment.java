package th.ac.kmitl.it59070025.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import th.ac.kmitl.it59070025.R;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setName();
    }

    public void setName() {

        SharedPreferences sp = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        EditText fname = getView().findViewById(R.id.home_firstname);
        EditText lname = getView().findViewById(R.id.home_lastname);
        // setname

    }
}
