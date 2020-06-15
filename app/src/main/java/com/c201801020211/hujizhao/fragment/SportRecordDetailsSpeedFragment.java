package com.c201801020211.hujizhao.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.c201801020211.hujizhao.commmon.bean.PathRecord;
import com.c201801020211.hujizhao.ui.BaseFragment;
import com.c201801020211.hujizhao.ui.activity.SportRecordDetailsActivity;
import com.c201801020211.hujizhao.R;

import java.text.DecimalFormat;

import butterknife.BindView;

public class SportRecordDetailsSpeedFragment extends BaseFragment {

    @BindView(R.id.tvDistribution)
    TextView tvDistribution;

    private PathRecord pathRecord = null;

    private DecimalFormat decimalFormat = new DecimalFormat("0.00");

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sportrecorddetailsspeed;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            pathRecord = bundle.getParcelable(SportRecordDetailsActivity.SPORT_DATA);
        }

        if (null != pathRecord)
            tvDistribution.setText(decimalFormat.format(pathRecord.getDistribution()));
    }

    @Override
    public void initListener() {

    }

}
