package com.sizu.mingteng.my_xianglekang.ui.fragment.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sizu.mingteng.my_xianglekang.R;
import com.sizu.mingteng.my_xianglekang.databinding.ItemConstellationLayoutBinding;

import java.util.List;

/**
 * Created by lenovo on 2017/5/27.
 */

public class ConstellationAdapter extends  RecyclerView.Adapter<ConstellationAdapter.ItemViewHolder> {
private Context mContext;
private List<String> mStringList;

public OnClickListener mOnClickListener;
private  int itemPosition=0;
public interface OnClickListener{
    void ItemonClick(int position);
}
    public  void setItemOnClickListener(OnClickListener mOnClickListener){
        this.mOnClickListener=mOnClickListener;
    }

    public ConstellationAdapter(Context context, List<String> strings) {
        mContext = context;
        mStringList = strings;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemConstellationLayoutBinding Binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_constellation_layout, parent, false);
        return new ItemViewHolder(Binding);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.mBinding.text.setText(mStringList.get(position));

        boolean b = itemPosition == position;
        setIsBackground(b,holder);

        holder.mBinding.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemPosition=position;
                boolean selected = v.isSelected();
                setIsBackground(selected,holder);
                if (mOnClickListener!=null)
                    mOnClickListener.ItemonClick(position);
            }
        });
    }


    /**
     * 设置选中背景
     * @param b
     * @param holder
     */
    private void setIsBackground(boolean b,ItemViewHolder holder) {
            if (b) {
                holder.mBinding.text.setTextColor(mContext.getResources().getColor(R.color.drop_down_selected));
                holder.mBinding.text.setBackgroundResource(R.drawable.check_bg);
            } else {
                holder.mBinding.text.setTextColor(mContext.getResources().getColor(R.color.drop_down_unselected));
                holder.mBinding.text.setBackgroundResource(R.drawable.uncheck_bg);
            }
    }


    @Override
    public int getItemCount() {
        return mStringList == null ? 0 : mStringList.size();
    }

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public ItemConstellationLayoutBinding mBinding;

    public ItemViewHolder(ItemConstellationLayoutBinding Binding) {
        super(Binding.getRoot());
        mBinding = Binding;
    }
}
}