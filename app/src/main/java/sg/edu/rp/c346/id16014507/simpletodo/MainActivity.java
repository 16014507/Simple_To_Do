package sg.edu.rp.c346.id16014507.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spnAddRemove;
    EditText etToDo;
    Button btnAdd, btnDelete, btnClear;
    ListView lvToDO;
    ArrayList<String> alToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddRemove = findViewById(R.id.spinner);
        etToDo = findViewById(R.id.editTextToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvToDO = findViewById(R.id.listViewToDo);

        alToDo = new ArrayList<String>();

        ArrayAdapter aaToDo = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alToDo);

        lvToDO.setAdapter(aaToDo);

        String text = spnAddRemove.getSelectedItem().toString();

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etToDo.setHint("Type a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        etToDo.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTodo = etToDo.getText().toString();
                alToDo.add(newTodo);
                aaToDo.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etToDo.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove" , Toast.LENGTH_SHORT).show();
                }
                else {
                    int deleteTodo = Integer.parseInt(etToDo.getText().toString());
                    if (deleteTodo > alToDo.size()-1) {
                        Toast.makeText(MainActivity.this, "Wrong index number" , Toast.LENGTH_SHORT).show();
                    }
                    else {
                        alToDo.remove(deleteTodo);
                        aaToDo.notifyDataSetChanged();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });

    }
}