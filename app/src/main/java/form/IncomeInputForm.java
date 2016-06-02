package form;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bigedo.task_4_adv_app.R;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

/**
 * Created by bigedo on 30/05/2016.
 */
public class IncomeInputForm {

//    //create object
//    @NotEmpty(messageId = R.string.expense_item_null_value)
//    EditText incomeItemEditText;
//
//    @RegExp(value = "^[0-9]+$", messageId = R.string.number_validation)
//    @NotEmpty(messageId = R.string.expense_item_null_value)
//    EditText incomeValueEditText;
//
//    View view;
//
//    public IncomeInputForm(View view) {
//        this.view = view;
//        initCmp();
//    }
//
//    private void initCmp(){
//        incomeItemEditText = (EditText)view.findViewById(R.id.income_item_edittext);
//        incomeValueEditText = (EditText)view.findViewById(R.id.income_value_edittext);
//
//        FormValidator.startLiveValidation(this, view, new SimpleErrorPopupCallback(view.getContext()));
//    }
//
//    public boolean isDataValid(Fragment fragment){
//        boolean res = FormValidator.validate(fragment.getContext(), this, new SimpleErrorPopupCallback(fragment.getContext()));
//        return res;
//    }
}
