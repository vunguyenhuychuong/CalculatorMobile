package com.example.easycaculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv, solutionTv;
    MaterialButton buttonC , buttonBrackOpen, buttonCBrackClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus,buttonMinus,buttonEquals;
    MaterialButton button0, button1, button2,button3, button4, button5, button6,button7, button8,button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        
        assignTd(buttonC, R.id.button_c);
        assignTd(buttonBrackOpen, R.id.button_open_bracket);
        assignTd(buttonCBrackClose, R.id.button_close_bracket);
        assignTd(buttonDivide, R.id.button_divide);
        assignTd(buttonMultiply, R.id.button_multiply);
        assignTd(buttonPlus, R.id.button_plus);
        assignTd(buttonMinus, R.id.button_minus);
        assignTd(buttonEquals, R.id.button_equal);
        assignTd(button0, R.id.button_0);
        assignTd(button1, R.id.button_1);
        assignTd(button2, R.id.button_2);
        assignTd(button3, R.id.button_3);
        assignTd(button4, R.id.button_4);
        assignTd(button5, R.id.button_5);
        assignTd(button6, R.id.button_6);
        assignTd(button7, R.id.button_7);
        assignTd(button8, R.id.button_8);
        assignTd(button9, R.id.button_9);
        assignTd(buttonAC, R.id.button_ac);
        assignTd(buttonDot, R.id.button_dot);

    }


    void assignTd(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Error")) {
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data){

        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        }catch(Exception e) {
            return "Error";
        }
    }
}