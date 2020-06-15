package com.c201801020211.hujizhao.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.c201801020211.hujizhao.commmon.bean.SportMotionRecord;
import com.c201801020211.hujizhao.commmon.utils.LogUtils;
import com.c201801020211.hujizhao.commmon.utils.MySp;
import com.c201801020211.hujizhao.db.DataManager;
import com.c201801020211.hujizhao.db.RealmHelper;
import com.c201801020211.hujizhao.ui.BaseActivity;
import com.c201801020211.hujizhao.ui.permission.PermissionHelper;
import com.c201801020211.hujizhao.ui.permission.PermissionListener;
import com.c201801020211.hujizhao.ui.permission.Permissions;
import com.c201801020211.hujizhao.R;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class SportsActivity extends BaseActivity {

    @BindView(R.id.tv_sport_mile)
    TextView tvSportMile;
    @BindView(R.id.tv_sport_count)
    TextView tvSportCount;
    @BindView(R.id.tv_sport_time)
    TextView tvSportTime;
    @BindView(R.id.btStart)
    Button btStart;

    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    private final int SPORT = 0x0012;

    private DataManager dataManager = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sports;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        dataManager = new DataManager(new RealmHelper());

        upDateUI();
    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.btStart)
    public void onViewClicked() {
        PermissionHelper.requestPermissions(this, Permissions.PERMISSIONS_LOCATION,
                getResources().getString(R.string.app_name) + "需要获取位置", new PermissionListener() {
                    @Override
                    public void onPassed() {
                        startActivityForResult(new Intent(SportsActivity.this, SportMapActivity.class), SPORT);
                    }
                });
    }

    private void upDateUI() {
        try {
            List<SportMotionRecord> records = dataManager.queryRecordList(Integer.parseInt(SPUtils.getInstance().getString(MySp.USERID, "0")));
            if (null != records) {

                double sportMile = 0;
                long sportTime = 0;
                for (SportMotionRecord record : records) {
                    sportMile += record.getDistance();
                    sportTime += record.getDuration();
                }
                tvSportMile.setText(decimalFormat.format(sportMile / 1000d));
                tvSportCount.setText(String.valueOf(records.size()));
                tvSportTime.setText(decimalFormat.format((double) sportTime / 60d));
            }
        } catch (Exception e) {
            LogUtils.e("获取运动数据失败", e);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case SPORT:
                upDateUI();
                setResult(RESULT_OK);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (null != dataManager)
            dataManager.closeRealm();
        super.onDestroy();
    }

}
