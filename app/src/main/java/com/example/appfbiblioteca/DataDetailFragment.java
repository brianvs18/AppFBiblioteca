package com.example.appfbiblioteca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.appfbiblioteca.models.LibrosModel;

public class DataDetailFragment extends Fragment {

    private static String id, nombre, autor, editorial;
    private Boolean active;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_detail, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv_data_detail_id, tv_data_detail_nombre, tv_data_detail_autor, tv_data_detail_editorial;
        tv_data_detail_id = view.findViewById(R.id.tv_data_detail_id);
        tv_data_detail_nombre = view.findViewById(R.id.tv_data_detail_nombre);
        tv_data_detail_autor = view.findViewById(R.id.tv_data_detail_autor);
        tv_data_detail_editorial = view.findViewById(R.id.tv_data_detail_editorial);

        tv_data_detail_id.setText(id);
        tv_data_detail_nombre.setText(nombre);
        tv_data_detail_autor.setText(autor);
        tv_data_detail_editorial.setText(editorial);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(DataDetailFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    static void receiveData(Bundle bundle){
        LibrosModel model = (LibrosModel) bundle.getSerializable("model");
        if(model != null){
            id = model.getId();
            nombre = model.getNombre();
            autor = model.getAutor();
            editorial = model.getEditorial();
        }
    }
}