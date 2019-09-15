package com.rsmartin.fuelapp.presentation.ui.login;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.rsmartin.fuelapp.R;

public class ForgotPasswordDialogFragment extends DialogFragment {

    public static final String TAG = "ForgotPasswordDialogFragment";

    ForgotPasswordListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.forgot_pass_fragment, null);
        EditText email = view.findViewById(R.id.email);

        builder.setView(view)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String emailToSend = email.getText().toString().trim();
                        listener.onDialogPositive(emailToSend);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ForgotPasswordListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement ForgotPasswordDialogFragment");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    public interface ForgotPasswordListener {
        void onDialogPositive(String email);
    }
}
