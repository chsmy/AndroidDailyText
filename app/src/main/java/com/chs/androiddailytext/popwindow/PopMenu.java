package com.chs.androiddailytext.popwindow;

/**
 * @author chs
 * date：2019-11-20 14:34
 * des：右上角弹出菜单
 */
public class PopMenu {

//    private void showStep() {
//        String [] arr = getResources().getStringArray(R.array.parts_change_all_opt);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View popView = inflater.inflate(R.layout.parts_pop_list, null);
//        RecyclerView recyclerView =  popView.findViewById(R.id.recycleview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.addItemDecoration(new RecyclerViewItemDecoration(this,SizeUtils.dp2px(1), false));
//        List<ItemFilter> data = new ArrayList<>();
//        data.add(new ItemFilter(arr[1],""));
//        data.add(new ItemFilter(arr[2],""));
//        data.add(new ItemFilter(arr[3],""));
//        data.add(new ItemFilter(arr[4],""));
//        BaseQuickAdapter adapter = new BaseQuickAdapter<ItemFilter, BaseViewHolder>(R.layout.item_filter_list,data) {
//            @Override
//            protected void convert(BaseViewHolder helper, ItemFilter item) {
//                TextView name = helper.getView(R.id.tv_name);
//                ViewGroup.LayoutParams layoutParams = name.getLayoutParams();
//                layoutParams.height = ScreenUtil.dip2px(PartsDetailActivity.this,48);
//                name.setLayoutParams(layoutParams);
//                name.setText(item.getName());
//            }
//        };
//        recyclerView.setAdapter(adapter);
//        popupWindow = new PopupWindow(popView, 4 * ScreenUtil.getScreenWidth(this) / 9, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//            }
//        });
//        LinearLayout llPop = popView.findViewById(R.id.ll_pop);
//        ShadowProperty sp = new ShadowProperty()
//                .setShadowColor(ContextCompat.getColor(this,R.color.shadow))
//                .setShadowDy(SizeUtils.dp2px(0.5f))
//                .setShadowRadius(SizeUtils.dp2px( 3))
//                .setShadowSide(ShadowProperty.LEFT | ShadowProperty.BOTTOM|ShadowProperty.TOP);
//        ShadowViewDrawable sd = new ShadowViewDrawable(sp, Color.TRANSPARENT, 0, 0);
//        ViewCompat.setBackground(llPop, sd);
//        llPop.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                popupWindow.dismiss();
//            }
//        });
//        popupWindow.showAsDropDown(mTitleBar,ScreenUtil.getScreenWidth(this)+ScreenUtil.dip2px(this,6),
//                -ScreenUtil.dip2px(this,6));
//    }

}
