package robot.com.rxbindingdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 使用Lambda表达式来
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rxbinding_t_toolbar)
    Toolbar rxbindingTToolbar;
    @BindView(R.id.rxbinding_et_usual_approach)
    EditText rxbindingEtUsualApproach;
    @BindView(R.id.rxbinding_et_reactive_approach)
    EditText rxbindingEtReactiveApproach;
    @BindView(R.id.rxbinding_tv_show)
    TextView rxbindingTvShow;
    @BindView(R.id.rxbinding_fab_fab)
    FloatingActionButton rxbindingFabFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        initFab();
        initEt();
    }

    // 非lambda的写法
    private void initToolbarNoLambda() {
        setSupportActionBar(rxbindingTToolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RxToolbar.itemClicks(rxbindingTToolbar).subscribe(new Action1<MenuItem>() {
            @Override
            public void call(MenuItem menuItem) {
                onToolItemClicked(menuItem);
            }
        });
    }

    // lambda的写法
    private void initToolbar() {
        setSupportActionBar(rxbindingTToolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RxToolbar.itemClicks(rxbindingTToolbar).subscribe(this::onToolItemClicked);
    }

    public void onToolItemClicked(MenuItem menuItem) {
        Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
    }

    private void initFab() {
        RxView.clicks(rxbindingFabFab).subscribe(this::onFabClicked);
    }

    /**
     * 没有参数的写法
     */
    public void onFabClicked(Void avoid) {
        Toast.makeText(this, "fab", Toast.LENGTH_SHORT).show();
    }

    private void initEt() {
        // Rx方式
        RxTextView.textChanges(rxbindingEtReactiveApproach).subscribe(rxbindingTvShow::setText);
    }

}