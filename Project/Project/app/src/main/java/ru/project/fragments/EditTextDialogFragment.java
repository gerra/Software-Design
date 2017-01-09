package ru.project.fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import ru.project.R;

public class EditTextDialogFragment extends DialogFragment {
    public static final String TAG = EditTextDialogFragment.class.getSimpleName();
    private static final String TITLE_KEY = "TITLE";

    public interface DialogClickListener {
        void onPositiveClick(String text);
    }

    private DialogClickListener dialogClickListener;

    public static EditTextDialogFragment factory(String title) {
        EditTextDialogFragment fragment = new EditTextDialogFragment();
        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle(getArguments().getString(TITLE_KEY))
                .setView(R.layout.dialog_edit_text)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    EditText editText = (EditText) getDialog().findViewById(R.id.edit_text);
                    String text = editText.getText().toString();
                    if (dialogClickListener != null) {
                        dialogClickListener.onPositiveClick(text);
                    }
                })
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> {

                })
                .create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DialogClickListener) {
            dialogClickListener = (DialogClickListener) context;
        }
    }
}
