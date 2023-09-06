package jp.ac.meijou.android.s221205006;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import jp.ac.meijou.android.s221205006.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefDataStore = PrefDataStore.getInstance(this);

        prefDataStore.getString("name")
                .ifPresent(name -> binding.text.setText(name));

        binding.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // テキストが更新される直前に呼ばれる
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 文字を1つ入力された時に呼ばれる
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // テキストが更新されたあとに呼ばれる
                //binding.text.setText(editable.toString());
            }
        });


        binding.saveButton.setOnClickListener(view -> {
            var text = binding.editText.getText().toString();
            prefDataStore.setString("name", text);
            Log.d("murayama","onCreate text: "+binding.text.getText());
        });

        binding.changeButton.setOnClickListener(view -> {
            var text = binding.editText.getText().toString();
            //prefDataStore.setString("name", text);
            binding.text.setText(text.toString());
        });

        binding.delete.setOnClickListener(view -> {
            var text = "";
            //prefDataStore.setString("name", text);
            binding.text.setText(text.toString());
        });

        Log.d("murayama","onCreate text: "+binding.text.getText());
    }

    @Override
    protected  void onStart() {
        super.onStart();
        prefDataStore.getString("name")
                .ifPresent(name -> binding.text.setText(name));
    }
}