package richard.ztesoft.com.segmentcontrol;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import info.hoang8f.android.segmented.SegmentedGroup;

public class MainActivity extends AppCompatActivity {

    private String TAG = "RichardSegmentControl";
    private ImageButton backButton;
    private SegmentedGroup segmentedGroup;
    private SearchVideoFragment searchVideoFragment;
    private DownloadManagerFragment downloadManagerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideActionBar();

        initViews();
    }
    /*
     * 隐藏导航栏
     */
    private void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    private void initViews(){
        searchVideoFragment = new SearchVideoFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container,  searchVideoFragment);
        transaction.commit();

        backButton = (ImageButton)findViewById(R.id.video_manager_back_btn);
        segmentedGroup= (SegmentedGroup)findViewById(R.id.video_segments);

        segmentedGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                Log.i(TAG,"radio" + i);
                if (i==R.id.button_search_video){

                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.container,  searchVideoFragment);
                    transaction.commit();
                }else if(i==R.id.button_download_manager){

                    if (downloadManagerFragment==null){
                        downloadManagerFragment = new DownloadManagerFragment();
                    }
                    FragmentManager manager = getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.container,  downloadManagerFragment);
                    transaction.commit();
                }
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}
