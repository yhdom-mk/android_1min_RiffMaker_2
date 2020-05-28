package com.example.myapp_1min_riffmaker_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CodeAdapter mAdapter;     //custom adapter
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<CodeItem> codeItems;      //public or private ?
    //private ArrayList<CodeItem> mCodeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createCodeList();
        buildRecyclerView();

        /*Intent intent = getIntent();
        CodeItem codeItem = intent.getParcelableExtra("Code Type");

        int imageRes = codeItem.getmImagesource();
        String line1 = codeItem.getmCodeview1();
        String line2 = codeItem.getmCodeview2();

        ImageView image_res = findViewById(R.id.image_activity2);
        image_res.setImageResource(imageRes);

        TextView code_view1 = findViewById(R.id.text1_activity2);
        code_view1.setText(line1);

        TextView code_view2 = findViewById(R.id.text2_activity2);
        code_view2.setText(line2); */
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CodeAdapter(codeItems);        //mCodeList -> codeItems  20200421

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CodeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //mCodeList.get(position);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("Code Type", codeItems.get(position));    //mCodeList -> codeItem 20200421

                startActivity(intent);
            }
        });

    }

    private void createCodeList() {
        codeItems = new ArrayList<>();

        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 01", "C →D →F →C "));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 02", "C →G →Am→F "));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 03", "C →A7 →Am→D7"));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 04", "C →B♭\n→Dm→G "));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 05", "Dm→G →E →A "));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 06", "Dm→F →Em→G7"));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 07", "Em→Am →B♭→G "));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 08", "Em→F  →G→Am"));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 09", "F →C →G →A "));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 10", "F →E♭→G →C "));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 11", "F →G →Em→Am"));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 12", "F →A7\n→Dm→G "));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 13", "G →Em7\n→Am7→C"));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 14", "GM7→F#m\n→Em7→D "));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 15", "G →Gaug\n→C →Cm"));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 16", "A →C7\n→D7→C7"));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 17", "Am→F →G →C "));
        codeItems.add(new CodeItem(R.mipmap.ic_launcher, "Code Type 18", "Am→AmM7\n→Am7→D7"));
    }
}
