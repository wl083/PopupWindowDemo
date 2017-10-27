package com.wl.popunwindowdemo.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wl.popunwindowdemo.MainActivity;
import com.wl.popunwindowdemo.R;

import java.util.concurrent.ExecutionException;

/**
 * Created by stone
 * On 2017/10/18
 * Describe: dialogfragment 的简单使用
 */

public class CustomDialogFragment extends DialogFragment {

    private CustomDialogFragmentInterface dialogFragmentInterface;
    public interface CustomDialogFragmentInterface{void setResult(String result);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.FrameDialogTheme);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainActivity){
            dialogFragmentInterface = (CustomDialogFragmentInterface) activity;
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setWindowAnimations(R.style.AnimScale);
        View view = inflater.inflate(R.layout.fragment_custom_dialog_fragment,null);
        view.findViewById(R.id.btn_dialogfragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogFragmentInterface != null) {
                    dialogFragmentInterface.setResult("surprise");
                }else{
                    throw new NullPointerException("dialogfragmentInterface is null");
                }
                dismiss();
            }
        });
        return view;
    }
}
