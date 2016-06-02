package layout;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.bigedo.task_4_adv_app.R;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;
import eu.inmite.android.lib.validations.form.callback.SimpleToastCallback;
import form.ExpenseInputForm;
import form.IncomeInputForm;
import helper.DatabaseHelper;
import helper.DbConstant;

import static xdroid.toaster.Toaster.toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TransactionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TransactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionFragment extends Fragment implements View.OnClickListener {

    //create view object
    View view;

    //create edittext object
    @NotEmpty(messageId = R.string.expense_item_null_value)
    EditText itemEditText;

    @RegExp(value = "^[0-9]+$", messageId = R.string.number_validation)
    @NotEmpty(messageId = R.string.expense_item_null_value)
    EditText amountEditText;

    //create textview object
    TextView resultTextView;

    //create button object
    Button saveButton;

    //create radio button group object
    RadioGroup actionRadioGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.transaction_fragment, container, false);

        this.initCmp();

        return view;
    }

    ExpenseInputForm eif;

    protected void initCmp() {
        resultTextView = (TextView) view.findViewById(R.id.transaction_result);

        //init edit text
        itemEditText = (EditText)view.findViewById(R.id.item_textview);
        amountEditText = (EditText)view.findViewById(R.id.amount_textview);

        //init form validator
        FormValidator.startLiveValidation(this, view, new SimpleErrorPopupCallback(getContext()));

        //init button
        saveButton = (Button) view.findViewById(R.id.save_button);

        //add button listener
        saveButton.setOnClickListener(this);

        //add listener for radio button
        actionRadioGroup = (RadioGroup) view.findViewById(R.id.action_radio_group);
        actionRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.outcome_radio_button:
                        toast("income");
                        break;
                    case R.id.income_radio_button:
                        toast("outcome");
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_button:
                //boolean res = eif.isDataValid(this);
                boolean result = FormValidator.validate(this, new SimpleErrorPopupCallback(this.getContext()));
                if (result) {
                    DatabaseHelper dbHelper = new DatabaseHelper(this.getContext());
                    String item = String.valueOf(itemEditText.getText());
                    int amount = Integer.parseInt(String.valueOf(amountEditText.getText()));
                    int selectedButton = actionRadioGroup.getCheckedRadioButtonId();
                    String type = null;
                    switch(selectedButton){
                        case R.id.income_radio_button:
                            type = DbConstant.TYPE_INCOME;
                            break;
                        case R.id.outcome_radio_button:
                            type = DbConstant.TYPE_OUTCOME;
                            break;
                        default:
                            break;
                    }
                    dbHelper.insertNewData(item, amount, type);
                    Cursor res;
                    res = dbHelper.getAllData();
                    if (res.getCount() == 0) {
                        resultTextView.append("asdasdasda");
                        return;
                    }
                    StringBuffer sbuffer = new StringBuffer();
                    while (res.moveToNext()) {
                        sbuffer.append("item :" + res.getString(0) + "\n" +
                                " amount : " + res.getString(2) + "\n" +
                                " type : " + res.getString(1) + "\n");
                        resultTextView.append(sbuffer);
                    }
                    resultTextView.append("expense " + Boolean.toString(result)+" income ");
                    cleanForm();
                } else {
                    resultTextView.append("expense " + Boolean.toString(result)+" "+actionRadioGroup.getCheckedRadioButtonId());
                }
            default:
                break;
        }
    }

    private void cleanForm(){
        itemEditText.setText("");
        amountEditText.setText("");
    }

}
