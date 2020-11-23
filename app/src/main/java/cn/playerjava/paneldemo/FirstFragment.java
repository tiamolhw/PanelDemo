package cn.playerjava.paneldemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.effective.android.panel.PanelSwitchHelper;
import com.effective.android.panel.interfaces.listener.OnEditFocusChangeListener;
import com.effective.android.panel.interfaces.listener.OnKeyboardStateListener;
import com.effective.android.panel.interfaces.listener.OnPanelChangeListener;
import com.effective.android.panel.view.panel.IPanelView;
import com.effective.android.panel.view.panel.PanelView;

import cn.playerjava.paneldemo.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding firstBinding;
    private final static String TAG = "FirstFragment";
    private PanelSwitchHelper mHelper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firstBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_first, container, false);
        firstBinding.setFragment(this);
        return firstBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mHelper == null) {
            mHelper = new PanelSwitchHelper.Builder(this)
                    .addKeyboardStateListener(new OnKeyboardStateListener() {
                        @Override
                        public void onKeyboardChange(boolean b, int i) {

                        }
                    })
                    .addEditTextFocusChangeListener((view, hasFocus) -> {
                        Log.d(TAG, "输入框是否获得焦点 : " + hasFocus);
                        if(hasFocus){
//                            scrollToBottom();
                        }
                    })
                    //可选
//                    .addKeyboardStateListener((visible, height) -> Log.d(TAG, "系统键盘是否可见 : " + visible + " 高度为：" + height))
                    //可选
//                    .addEditTextFocusChangeListener((view, hasFocus) -> {
//                        Log.d(TAG, "输入框是否获得焦点 : " + hasFocus);
//                        if(hasFocus){
////                            scrollToBottom();
//                        }
//                    })
                    //可选
                    .addViewClickListener(view -> {
                        switch (view.getId()){
                            case R.id.btn_send_menu:
                            case R.id.btn_send_face: {
//                                scrollToBottom();
                            }
                        }
                        Log.d(TAG, "点击了View : " + view);
                    })
                    //可选
                    .addPanelChangeListener(new OnPanelChangeListener() {

                        @Override
                        public void onKeyboard() {
                            Log.d(TAG, "唤起系统输入法");
//                            mBinding.emotionBtn.setSelected(false);
                        }

                        @Override
                        public void onNone() {
                            Log.d(TAG, "隐藏所有面板");
//                            mBinding.emotionBtn.setSelected(false);
                        }

                        @Override
                        public void onPanel(IPanelView view) {
                            Log.d(TAG, "唤起面板 : " + view);
//                            if(view instanceof PanelView){
//                                mBinding.emotionBtn.setSelected(((PanelView)view).getId() == R.id.panel_emotion ? true : false);
//                            }
                        }

                        @Override
                        public void onPanelSizeChange(IPanelView panelView, boolean portrait, int oldWidth, int oldHeight, int width, int height) {
                            if(panelView instanceof PanelView){
                                switch (((PanelView)panelView).getId()) {
                                    case R.id.panel_emotion: {
//                                        EmotionPagerView pagerView = mBinding.getRoot().findViewById(R.id.view_pager);
//                                        int viewPagerSize = height - DisplayUtils.dip2px(getContext(), 30f);
//                                        pagerView.buildEmotionViews(
//                                                (PageIndicatorView) mBinding.getRoot().findViewById(R.id.pageIndicatorView),
//                                                mBinding.editText,
//                                                Emotions.getEmotions(), width, viewPagerSize);
                                        break;
                                    }
//                                    case R.id.panel_addition: {
//                                        //auto center,nothing to do
//                                        break;
//                                    }
                                }
                            }
                        }
                    })
                    .logTrack(true)             //output log
                    .build();
        }
//        mBinding.recyclerView.setPanelSwitchHelper(mHelper);
    }
    public void onGoToNext(View view){
        Navigation.findNavController(view)
                .navigate(R.id.action_fristFragment_to_secondFragment);
    }
}