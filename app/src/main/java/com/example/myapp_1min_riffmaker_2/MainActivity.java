package com.example.myapp_1min_riffmaker_2;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        TextView tv = findViewById(R.id.index);
        Button bt = findViewById(R.id.code_button);
        CodeDatabase db = AppDatabaseSingleton.getInstance((getApplicationContext()));

        bt.setOnClickListener(new ButtonClickListener(this, db, tv));

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

    private class ButtonClickListener implements View.OnClickListener {
        private Activity activity;
        private CodeDatabase db;
        private TextView tv;

        public ButtonClickListener(Activity activity, CodeDatabase db, TextView tv) {
            this.activity = activity;
            this.db = db;
            this.tv = tv;
        }

        @Override
        public void onClick(View view) {
            new DataStoreAsyncTask(db, activity, tv).execute();
        }
    }

    private static class DataStoreAsyncTask extends AsyncTask<Void, Void, Integer> {
        private WeakReference<Activity> weakActivity;
        private CodeDatabase db;
        private TextView textView;
        private StringBuffer sb;

        public DataStoreAsyncTask(CodeDatabase db, Activity activity, TextView textView) {
            this.db = db;
            weakActivity = new WeakReference<>(activity);
            this.textView = textView;
        }

        @Override
        protected Integer doInBackground(Void...params) {
            CodeDao codeDao = db.codeDao();
            codeDao.insert(new Code(new Timestamp(System.currentTimeMillis()).toString()));

            sb = new StringBuffer();
            List<Code> codeList = codeDao.getAll();
            for(Code cd: codeList) {
                sb.append(cd.getCode()).append("\n");
            }
            return 0;
        }
        @Override
        protected void onPostExecute(Integer integer) {
            Activity activity = weakActivity.get();
            if(activity == null){
                return;
            }
            textView.setText((sb.toString()));
        }
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

    @UiThread
    private void asyncRead() {
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.obj != null) {
//                    allCode = (List<Code>) msg.obj;
//                    callback.onReadCodeCompleted(allCode);
                }
            }
          //処理結果受け取る
//            BackgroundTaskRead backgroundTaskRead = new BackgroundTaskRead(handler, codeDao, codeItems);
//            ExecutorService.submit(backgroundTaskRead);
//            excutorService.submit(backgroundTaskRead);
        };
    }

    private static class BackgroundTaskRead implements Runnable {
        private final Handler handler;
        private CodeDao codeDao;
        private List<Code> code;

        BackgroundTaskRead(Handler handler, CodeDao codeDao, List<Code> code) {
            this.handler = handler;
            this.codeDao = codeDao;
            this.code = code;
        }

        @WorkerThread
        @Override
        public void run() {
            codeDao.getAll();

            //スレッドプールの作成
            ExecutorService pool = Executors.newFixedThreadPool(3);
            try {
                BackgroundTaskRead backgroundTaskRead = new BackgroundTaskRead(handler, codeDao, code);
                pool.submit(backgroundTaskRead);
            } catch(Exception e)
            {
                System.out.println("Exception:" + e);
            }finally
            {
                pool.shutdown();
            }
        }
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
