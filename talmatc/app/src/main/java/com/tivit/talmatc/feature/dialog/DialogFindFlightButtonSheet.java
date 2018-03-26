package com.tivit.talmatc.feature.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.data.local.constant.DigitEnum;

/**
 * Created by Antonio.Valdivieso on 12/03/2018.
 */

public class DialogFindFlightButtonSheet extends BottomSheetDialogFragment implements  View.OnClickListener{

    // Interfaz de comunicación
    OnSimpleDialogListener listener;

    TextView tvCantElements;
    Button btnOne ;
    Button btnTwo ;
    Button btnThree ;
    Button btnFour ;
    Button btnPlus ;
    Button btnOk ;


    String valor;
    int position;

    public DialogFindFlightButtonSheet(){
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return createSimpleDialog();
    }

    public void setValue(String valor, int position){
        this.valor = valor;
        this.position = position;
        if(this.tvCantElements != null){
            this.tvCantElements.setText(valor);
        }
    }

    public void setValue(String valor){
        this.valor = valor;
        if(this.tvCantElements != null){
            this.tvCantElements.setText(valor);
        }
    }

    public String getValue(){ return (tvCantElements.getText()==null||tvCantElements.getText().equals(""))?"0":tvCantElements.getText().toString(); }
    public int getPosition(){ return position; }
    /**
     * Crea un diálogo de alerta sencillo
     * @return Nuevo diálogo
     */
    public AlertDialog createSimpleDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Seleccionar Cantidad");

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.dlg_comp_numerico1_4, null);
        builder.setView(v);

        tvCantElements = (TextView ) v.findViewById(R.id.id_tv_cantelements);
        tvCantElements.setText(valor);
        btnOne      = (Button) v.findViewById(R.id.id_ib_one);
        btnTwo      = (Button) v.findViewById(R.id.id_ib_two);
        btnThree    = (Button) v.findViewById(R.id.id_ib_three);
        btnFour     = (Button) v.findViewById(R.id.id_ib_four);
        btnPlus     = (Button) v.findViewById(R.id.id_ib_plus);
        btnOk       = (Button) v.findViewById(R.id.id_ib_ok);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnPlus.setOnClickListener(this);

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnSimpleDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
                    " debe implementar DialogListener");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.id_ib_one :
                setValue(DigitEnum.DIGIT_ONE.getValue());
                break;
            case R.id.id_ib_two :
                setValue(DigitEnum.DIGIT_TWO.getValue());
                break;
            case R.id.id_ib_three :
                setValue(DigitEnum.DIGIT_THREE.getValue());
                break;
            case R.id.id_ib_four :
                setValue(DigitEnum.DIGIT_FOUR.getValue());
                break;
            case R.id.id_ib_plus :
                setValue(String.valueOf( Integer.parseInt(getValue())+1 ));
                break;
            case R.id.id_ib_ok :
                listener.onCloseDialog();
                break;
        }
    }

    public interface OnSimpleDialogListener {
        void onCloseDialog();
    }
}
