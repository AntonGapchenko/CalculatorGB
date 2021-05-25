package com.example.calculatorgb;

import java.io.Serializable;

import static java.lang.Double.*;

public class Calculator implements Serializable {
    private double firstArg;
    private double secondArg;


    private int actionSelected;
    private StringBuilder inputString = new StringBuilder();
    private State state;


    private enum State {
        firstArgInput,
        operationSelected,
        secondArgInput,
        resultShow
    }

    public Calculator() {
        state = State.firstArgInput;
    }

    public void onNumberPressed(int buttonId) {
        if (state == State.resultShow) {
            state = State.firstArgInput;
            inputString.setLength(0);
        }
        if (state == State.operationSelected) {
            state = State.secondArgInput;
            inputString.setLength(0);
        }
        if (inputString.length() < 9) {
            switch (buttonId) {
                case R.id.zero_btn:
                    inputString.append("0");
                    break;
                case R.id.one_btn:
                    inputString.append("1");
                    break;
                case R.id.two_btn:
                    inputString.append("2");
                    break;
                case R.id.three_btn:
                    inputString.append("3");
                    break;
                case R.id.four_btn:
                    inputString.append("4");
                    break;
                case R.id.five_btn:
                    inputString.append("5");
                    break;
                case R.id.six_btn:
                    inputString.append("6");
                    break;
                case R.id.seven_btn:
                    inputString.append("7");
                    break;
                case R.id.eight_btn:
                    inputString.append("8");
                    break;
                case R.id.nine_btn:
                    inputString.append("9");
                    break;
                case R.id.point:
                    inputString.append(".");
                    break;

            }
        }


    }

    public void onActionPressed(int actionId) {
        if (actionId == R.id.equals && state == State.secondArgInput) {
            secondArg = parseDouble(inputString.toString());
            state = State.resultShow;
            inputString.setLength(0);
            switch (actionSelected) {
                case R.id.plus:
                    inputString.append(firstArg + secondArg);
                    break;
                case R.id.minus:
                    inputString.append(firstArg - secondArg);
                    break;
                case R.id.multiply:
                    inputString.append(firstArg * secondArg);
                    break;
                case R.id.divide:
                    inputString.append(firstArg / secondArg);
                    break;

            }

        } else if (inputString.length() > 0 && state == State.firstArgInput) {
            firstArg = parseDouble(inputString.toString());
            state = State.operationSelected;
            actionSelected = actionId;
        }

    }

    private char getOperationChar() {
        switch (actionSelected) {
            case R.id.plus:
                return '+';
            case R.id.minus:
                return '-';
            case R.id.multiply:
                return '*';
            default:
                return '/';


        }
    }

    public String getText() {
        StringBuilder str = new StringBuilder();
        switch (state) {
            case operationSelected:
                return str.append(firstArg).append(getOperationChar()).toString();
            case secondArgInput:
                return str.append(firstArg)
                        .append(getOperationChar())
                        .append(inputString).toString();
            case resultShow:
                return str.append(firstArg)
                        .append(getOperationChar())
                        .append(secondArg).append("=")
                        .append(inputString.toString()).toString();
            default:
                return inputString.toString();
        }
    }


    public void reset() {
        state = State.firstArgInput;
        inputString.setLength(0);
    }

    public void backspace() {
        if (inputString.length() != 0) {
            inputString.setLength(inputString.length() - 1);
        }

    }
}
