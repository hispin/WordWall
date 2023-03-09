package com.example.wordwall;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> dictionary;
    private int haim = 6;
    private TextView st;
    private EditText et;
    private TextView mispar;
    private Button ok;
    private String random;
    private RecyclerView rc;
    private ArrayList<String> characters = new ArrayList<String>();
    private MyNikodAdapter myNikodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        createDictionary();
        random = getRandomWord();
        st.setText(random);
        for (int i = 0; i < random.length(); i++) {
            characters.add(random.charAt(i) + "");
        }
        Log.d("", "");
        myNikodAdapter = new MyNikodAdapter(characters, new MyNikodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Character item) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rc.setLayoutManager(linearLayoutManager);
        rc.setAdapter(myNikodAdapter);
        rc.setHasFixedSize(true);
    }

    private void createDictionary() {
        dictionary = new ArrayList<String>();
        BufferedReader dict = null; //Holds the dictionary file
        AssetManager am = this.getAssets();

        try {
            //dictionary.txt should be in the assets folder.
            dict = new BufferedReader(new InputStreamReader(am.open("israeli_place_names.txt")));

            while ((dict.readLine()) != null) {
                dictionary.add(dict.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            dict.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Precondition: the dictionary has been created.
    public String getRandomWord() {
        return dictionary.get((int) (Math.random() * dictionary.size() - 1));
    }

    public void initViews() {
        ok = findViewById(R.id.ok);
        ok.setOnClickListener(this);
        st = findViewById(R.id.st);
        et = findViewById(R.id.et);
        mispar = findViewById(R.id.mispar);
        rc = findViewById(R.id.rc);
    }

    @Override
    public void onClick(View view) {
        boolean b = false;
        for (int i = 0; i < random.length(); i++) {
            if (et.getText().charAt(0) == random.charAt(i))
                b = true;
        }
        if (b == false)
            haim--;
        mispar.setText(haim + "");
        if (b) {
            for (int i = 0; i < characters.size(); i++) {
                if (et.getText().charAt(0) == characters.get(i).charAt(0))
                    myNikodAdapter.isvisible();
            }
        }

    }
}