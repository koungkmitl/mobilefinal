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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import th.ac.kmitl.it59070025.R;
import th.ac.kmitl.it59070025.auth.LoginFragment;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private List<String> list;

    public HomeFragment() {
        list = new ArrayList<>();

        list.add("profile setup");
        list.add("my friends");
        list.add("sign out");
    }

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
        menuList();
    }

    public void setName() {

        SharedPreferences sp = getActivity().getSharedPreferences("login", MODE_PRIVATE);
        TextView fname = getView().findViewById(R.id.home_firstname);
        TextView lname = getView().findViewById(R.id.home_lastname);
        // setname
        fname.setText(sp.getString("firstname", ""));
        lname.setText(sp.getString("lastname", ""));

    }

    public void menuList() {
        final ArrayAdapter<String> menuAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                list
        );

        ListView menuList = getView().findViewById(R.id.home_list);

        menuList.setAdapter(menuAdapter);

        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // profile setup
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new ProfileFragment())
                            .addToBackStack(null)
                            .commit();
                } else if (position == 1) {
                    // myfriend
                } else if (position == 2) {
                    // signout
                    SharedPreferences sp = getActivity().getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    Log.d("login", sp.getString("firstname", "error"));
                    Log.d("login", sp.getString("lastname", "error2"));
                    editor.remove("username");
                    editor.apply();

                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoginFragment())
                            .commit();
                }
            }
        });
    }
}
