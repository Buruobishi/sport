package com.c201801020211.hujizhao.ui.adapter;

import androidx.annotation.Nullable;

import com.c201801020211.hujizhao.commmon.bean.PathRecord;
import com.c201801020211.hujizhao.sport_motion.MotionUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.c201801020211.hujizhao.R;

import java.text.DecimalFormat;
import java.util.List;


public class SportCalendarAdapter extends BaseQuickAdapter<PathRecord, BaseViewHolder> {

    private DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private DecimalFormat intFormat = new DecimalFormat("#");

    public SportCalendarAdapter(int layoutResId, @Nullable List<PathRecord> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PathRecord item) {
        helper.setText(R.id.distance, decimalFormat.format(item.getDistance() / 1000d));
        helper.setText(R.id.duration, MotionUtils.formatseconds(item.getDuration()));
        helper.setText(R.id.calorie, intFormat.format(item.getCalorie()));
    }
}
