package com.freedom.sword_offer;

import java.util.HashMap;
import java.util.Map;

public class Offer20 {

    public static void main(String[] args) {
        Offer20 offer20 = new Offer20();
        boolean number = offer20.isNumber(" 1.e1");
        System.out.println(number);
    }

    public boolean isNumber(String s) {
        Map<State,Map<CharType,State>> map = new HashMap<>();
        Map<CharType,State> initialMap = new HashMap<CharType, State>(){{
            put(CharType.BLANK, State.STATE_INITIAL);
            put(CharType.DOT, State.STATE_POINT_WITHOUT_INT);
            put(CharType.SIGN, State.STATE_INT_SIGN);
            put(CharType.NUMBER, State.STATE_INTEGER);
        }};
        map.put(State.STATE_INITIAL, initialMap);

        Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.STATE_INTEGER);
            put(CharType.DOT, State.STATE_POINT_WITHOUT_INT);
        }};
        map.put(State.STATE_INT_SIGN, intSignMap);
        Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.STATE_INTEGER);
            put(CharType.EXP, State.STATE_EXP);
            put(CharType.DOT, State.STATE_POINT);
            put(CharType.BLANK, State.STATE_END);
        }};
        map.put(State.STATE_INTEGER, integerMap);
        Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.STATE_FRACTION);
            put(CharType.EXP, State.STATE_EXP);
            put(CharType.BLANK, State.STATE_END);
        }};
        map.put(State.STATE_POINT, pointMap);
        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.STATE_FRACTION);
        }};
        map.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);
        Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.STATE_FRACTION);
            put(CharType.EXP, State.STATE_EXP);
            put(CharType.BLANK, State.STATE_END);
        }};
        map.put(State.STATE_FRACTION, fractionMap);
        Map<CharType, State> expMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.SIGN, State.STATE_EXP_SIGN);
        }};
        map.put(State.STATE_EXP, expMap);
        Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.STATE_EXP_NUMBER);
        }};
        map.put(State.STATE_EXP_SIGN, expSignMap);
        Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.BLANK, State.STATE_END);
        }};
        map.put(State.STATE_EXP_NUMBER, expNumberMap);
        Map<CharType, State> endMap = new HashMap<CharType, State>() {{
            put(CharType.BLANK, State.STATE_END);
        }};
        map.put(State.STATE_END, endMap);

        State state = State.STATE_INITIAL;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            CharType charType = getCharType(s.charAt(i));
            if (!map.get(state).containsKey(charType)) {
                return false;
            } else {
                state = map.get(state).get(charType);
            }
        }
        return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION
                || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
    }

    private CharType getCharType(char c) {
        if (c >= '0' && c <= '9') {
            return CharType.NUMBER;
        } else if (c == 'e' || c == 'E') {
            return CharType.EXP;
        } else if (c == '+' || c == '-') {
            return CharType.SIGN;
        } else if (c == '.') {
            return CharType.DOT;
        } else if (c == ' ') {
            return CharType.BLANK;
        } else {
            return CharType.ILLEGAL;
        }
    }

    enum State {
        STATE_INITIAL, // 开头空格
        STATE_INT_SIGN, // 正负号
        STATE_INTEGER, // 整数
        STATE_POINT, // 小数点，左有整数
        STATE_POINT_WITHOUT_INT, // 小数点，左无整数
        STATE_FRACTION, // 小数
        STATE_EXP, // 字符e
        STATE_EXP_SIGN, // 指数符号
        STATE_EXP_NUMBER, // 指数数字
        STATE_END // 末尾空格
    }

    enum CharType {
        BLANK,
        DOT,
        SIGN,
        EXP,
        NUMBER,
        ILLEGAL
    }


}
