package com.unimus.apitodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText inputTodo;
    Button btnAdd;
    RecyclerView rvTodo;
    TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTodo = findViewById(R.id.inputTodo);
        btnAdd = findViewById(R.id.btnAdd);
        rvTodo = findViewById(R.id.rvTodo);
        rvTodo.setLayoutManager(new LinearLayoutManager(this));

        loadTodo();
        btnAdd.setOnClickListener(v->addTodo());

    }

    private void addTodo() {
        // Ambil text dan hapus spasi di awal/akhir menggunakan trim()
        String title = inputTodo.getText().toString().trim();

        // --- MULAI VALIDASI ---
        if (title.isEmpty()) {
            Toast.makeText(this, "Tugas tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            return; // Hentikan proses jika kosong
        }
        // --- SELESAI VALIDASI ---

        ApiClient.getService().addTodo(title).enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.isSuccessful() && response.body().status) {
                    Toast.makeText(MainActivity.this, "Berhasil menambahkan tugas", Toast.LENGTH_SHORT).show();
                    inputTodo.setText("");
                    loadTodo();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal menambahkan data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTodo() {
        ApiClient.getService().getTodo().enqueue(new Callback<TodoResponse>() {
            @Override
            public void onResponse(Call<TodoResponse> call, Response<TodoResponse> r) {
                if (r.body().status){
                    adapter = new TodoAdapter(r.body().getData());
                    rvTodo.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<TodoResponse> call, Throwable t) {

            }
        });
    }


}
