package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bigedo.task_4_adv_app.R;

import java.util.ArrayList;
import java.util.List;

import model.CashFlow;

/**
 * Created by bigedo on 6/1/2016.
 */
public class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextViewHolder> {

    private List<CashFlow> dataset;

    private List<CashFlow> cashFlowList;

    public static class TextViewHolder extends RecyclerView.ViewHolder{
        public TextView item, amount, type;
        public TextViewHolder(View v) {
            super(v);
            item = (TextView) v.findViewById(R.id.item);
            amount = (TextView) v.findViewById(R.id.amount);
            type = (TextView) v.findViewById(R.id.type);
        }
    }

    public TextAdapter(List<CashFlow> dataSet){
        this.dataset = dataSet;
    }

    @Override
    public TextAdapter.TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //get the recycler view that will hold the data
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view, parent, false);

        TextViewHolder vh = new TextViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TextAdapter.TextViewHolder holder, int position) {
        holder.item.setText(this.dataset.get(position).getItem());
        holder.amount.setText(Integer.toString(this.dataset.get(position).getAmount()));
        holder.type.setText(this.dataset.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return this.dataset.size();
    }
}
