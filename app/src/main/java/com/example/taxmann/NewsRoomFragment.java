package com.example.taxmann;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsRoomFragment extends Fragment {
    private onFragmentBtnSelected listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsroom, container, false);
        //Button clickme = (Button) view.findViewById(R.id.btn_load);
        Button logout = (Button) view.findViewById(R.id.btn_logout);
//        clickme.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onBtnSelected();           //method in MainActivity.java which opens a new fragment (chat)
//            }
//        });
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                Toast.makeText(this, "Logout button clicked.", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(getActivity(), LoginActivity.class);
                in.putExtra("some", "some data");
                startActivity(in);
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onFragmentBtnSelected){
            listener= (onFragmentBtnSelected) context;
        }else{
            throw new ClassCastException(context.toString() + "must implement listener");
        }
;    }

    public interface onFragmentBtnSelected{
        public void onBtnSelected();
    }
}
