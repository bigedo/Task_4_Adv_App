package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bigedo.task_4_adv_app.R;

import java.util.List;

import model.CashFlow;

/**
 * Created by bigedo on 5/31/2016.
 */
public class CashFlowAdapter extends RecyclerView.Adapter<CashFlowAdapter.CashFlowHolder> {

    private List<CashFlow> cashFlowList;

    public class CashFlowHolder extends RecyclerView.ViewHolder{
        public TextView itemView, amountView, typeView;

        public CashFlowHolder(View view){
            super(view);
            itemView = (TextView)view.findViewById(R.id.item_name);
            amountView = (TextView)view.findViewById(R.id.amount_view);
            typeView = (TextView)view.findViewById(R.id.type_view);
        }
    }

    public CashFlowAdapter(List<CashFlow> cashFlowList){
        this.cashFlowList = cashFlowList;
    }

    @Override
    public CashFlowAdapter.CashFlowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.income_detail, parent, false);

        return new CashFlowHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CashFlowHolder holder, int position) {
        CashFlow cashflow = cashFlowList.get(position);
        holder.itemView.setText(cashflow.getItem());
        holder.amountView.setText(cashflow.getAmount());
        holder.typeView.setText(cashflow.getType());
    }

    @Override
    public int getItemCount() {
        return cashFlowList.size();
    }
}
