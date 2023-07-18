package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

/*
9. Create the simple calculator shown below also perform appropriate operation
*/

public class MainActivity extends AppCompatActivity {

    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,btzero,btadd,btsub,btdiv,btmulti, btdot,btclear,btequal;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1=(Button) findViewById(R.id.one1);
        bt2=(Button) findViewById(R.id.two2);
        bt3=(Button) findViewById(R.id.three3);
        bt4=(Button) findViewById(R.id.four4);
        bt5=(Button) findViewById(R.id.five5);
        bt6=(Button) findViewById(R.id.six6);
        bt7=(Button) findViewById(R.id.seven7);
        bt8=(Button) findViewById(R.id.eight8);
        bt9=(Button) findViewById(R.id.nine9);
        btzero=(Button) findViewById(R.id.zeros);
        btadd=(Button) findViewById(R.id.additi);
        btsub=(Button) findViewById(R.id.substra);
        btdiv=(Button) findViewById(R.id.division);
        btmulti=(Button) findViewById(R.id.multipi);
        btdot=(Button) findViewById(R.id.dott);
        btclear=(Button) findViewById(R.id.clears);
        btequal=(Button) findViewById(R.id.equal);
        tv= (TextView) findViewById(R.id.display);

        bt1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()==" ")
                    tv.setText("1");
                else
                    tv.setText(tv.getText().toString()+"1");
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()==" ")
                    tv.setText("2");
                else
                    tv.setText(tv.getText().toString()+"2");
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()==" ")
                    tv.setText("3");
                else
                    tv.setText(tv.getText().toString()+"3");
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()==" ")
                    tv.setText("4");
                else
                    tv.setText(tv.getText().toString()+"4");
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()==" ")
                    tv.setText("5");
                else
                    tv.setText(tv.getText().toString()+"5");
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()==" ")
                    tv.setText("6");
                else
                    tv.setText(tv.getText().toString()+"6");
            }
        });

        bt7.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()==" ")
                    tv.setText("7");
                else
                    tv.setText(tv.getText().toString()+"7");
            }
        });

        bt8.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()==" ")
                    tv.setText("8");
                else
                    tv.setText(tv.getText().toString()+"8");
            }
        });

        bt9.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()==" ")
                    tv.setText("9");
                else
                    tv.setText(tv.getText().toString()+"9");
            }
        });

        btzero.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()==" ")
                    tv.setText("0");
                else
                    tv.setText(tv.getText().toString()+"0");
            }
        });

        btadd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()=="")
                    tv.setText("");
                else
                if(!check_last_character(tv.getText().toString()))
                    tv.setText(tv.getText().toString()+"+");
            }
        });

        btsub.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()=="")
                    tv.setText("");
                else
                if(!check_last_character(tv.getText().toString()))
                    tv.setText(tv.getText().toString()+"-");
            }
        });

        btdiv.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()=="")
                    tv.setText("");
                else
                if(!check_last_character(tv.getText().toString()))
                    tv.setText(tv.getText().toString()+"÷");
            }
        });

        btmulti.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(tv.getText()=="")
                    tv.setText("");
                else
                if(!check_last_character(tv.getText().toString()))
                    tv.setText(tv.getText().toString()+"*");
            }
        });

        btdot.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String temp = tv.getText().toString();
                if(temp.equals(""))
                    tv.setText("0.");
                else
                if(temp.length() != 0){
                    if(temp.charAt(temp.length()-1) == '.')
                        tv.setText("");
                    if(temp.charAt(temp.length()-1) == '*' || temp.charAt(temp.length()-1) == '÷' || temp.charAt(temp.length()-1) == '+' || temp.charAt(temp.length()-1) == '-')
                        tv.setText(tv.getText().toString()+"0.");
                }
            }
        });

        btclear.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                tv.setText("");
            }
        });

        btequal.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String ch = tv.getText().toString();
                if(!ch.equals("")) {
                    double answer = evaluatePostfix(ch);
                    if ((answer % 1) == 0)
                        tv.setText(Integer.toString((int) answer));
                    else
                        tv.setText(Double.toString(answer));
                }
            }
        });
    }

    static boolean check_last_character(String str){
        if(str.length() != 0){
            char c = str.charAt(str.length()-1);
            return c == '+' || c == '-' || c == '÷' || c == '*';
        }
        return false;
    }

    public static int precedence(char x){
        if(x=='^')
            return 2;
        else if(x=='*' || x=='÷')
            return 1;
        else if(x=='+' || x=='-')
            return 0;
        return -1;
    }

    public static String InfixToPostfix(String str){
        Stack<Character> stk= new Stack<>();             // used for converting infix to postfix
        StringBuilder ans= new StringBuilder();                // string containing our final answer
        int n= str.length();
        char x;
        for (int i = 0; i <n ; i++) {
            x= str.charAt(i);
            if(x>='0' && x<='9'){
                ans.append(x);
            }
            else if(x=='(')     // push directly in the stack
                stk.push('(');
            else if(x==')'){
                while(!stk.isEmpty() && stk.peek()!='(')          // keep popping till opening bracket is found
                    ans.append(stk.pop());
                if(!stk.isEmpty())
                    stk.pop();
            }
            else{
                while(!stk.isEmpty() && precedence(stk.peek())>=precedence(x))    // remove all higher precedence values
                    ans.append(stk.pop());
                stk.push(x);
            }
        }
        while(!stk.isEmpty())
            ans.append(stk.pop());
        return ans.toString();
    }

    static double evaluatePostfix(String str)
    {
        String exp = InfixToPostfix(str);
        // Create a stack
        Stack<Double> stack = new Stack<>();
        // Scan all characters one by one
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            // If the scanned character is an operand
            // (number here), push it to the stack.
            if (Character.isDigit(c))
                stack.push((double) (c - '0'));

                //  If the scanned character is an operator, pop
                //  two elements from stack apply the operator
            else {
                double val1 = stack.pop();
                double val2 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '÷':
                        stack.push(val2 / val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                }
            }
        }
        return stack.pop();
    }
}









