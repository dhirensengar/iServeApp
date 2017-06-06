package com.cears.serviceapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cears.serviceapp.R;
import com.cears.serviceapp.models.GetAllJob_Jobs;

import java.util.ArrayList;
import java.util.List;


public class SaveAdapter extends BaseAdapter {
    Context mContext;
    private List<GetAllJob_Jobs> quesSubResponses = new ArrayList<>();

    public SaveAdapter(Context mContext, List<GetAllJob_Jobs> quesSubResponses) {
        this.mContext = mContext;
        this.quesSubResponses = quesSubResponses;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return quesSubResponses.size();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_save_custom_adapter, null);
        CardView cardView = (CardView) view.findViewById(R.id.card_view);
        TextView mJobTextView = (TextView) view.findViewById(R.id.textViewTokenNumber);
        TextView mEpmNumberTextView = (TextView) view.findViewById(R.id.textViewEquipmentNumber);
        TextView mEpmTypeTextView = (TextView) view.findViewById(R.id.textViewEquipmentType);
        final TextView mServiceTypeTextView = (TextView) view.findViewById(R.id.textViewServiceName);
        TextView mDeleteTextView = (TextView) view.findViewById(R.id.textViewDelete);
        TextView mSubmitTextView = (TextView) view.findViewById(R.id.textViewSubmit);

        mDeleteTextView.setVisibility(View.GONE);

        if (quesSubResponses.size() > 0) {
     /*       mJobTextView.setText("Question ID # : " + quesSubResponses.get(position).getAll_jobs_Qid());
            mEpmNumberTextView.setText("Question : " + quesSubResponses.get(position).getAll_jobs_Question());*/

            if (quesSubResponses.get(position).getAll_job_ans().equals("1")) {
                mEpmNumberTextView.setText("Answer : Repaired");
            } else {
                mEpmNumberTextView.setText("Answer : No");
            }
        }

        return view;
    }

    public void appALL(List<GetAllJob_Jobs> data) {
        if (data != null) {
            quesSubResponses.addAll(data);
            notifyDataSetChanged();
        }
    }
}
