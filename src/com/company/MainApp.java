package com.company;

        import java.util.Arrays;
        import java.util.List;
        import java.util.Objects;
        import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) {
        final String INPUTEXP = ReadInput.read();
        //入力式（文字列）
        String[] expression =INPUTEXP.split("");
        // 配列からリストへ変換
        List<String> expList = Arrays.asList(expression);

        // フィルタ処理（空白をリストから除外）
        expList = expList.stream()//
                .filter(tmp -> !Objects.equals(tmp, " ")) //
                .collect(Collectors.toList());

        // リストから配列へ戻す
        String[] str = new String[expList.size()];
        expression = expList.toArray(str);

        //数値を入れておく配列
        int[] value = new int[100];
        //演算子を入れておく配列
        String[] operator = new String[100];
        //計算の優先順位
        int[] priority = new int[100];
        //演算子の数
        int opCnt = 0;
        //カッコ
        int nest = 0;
        value[0] = 0;

        // 解析処理（入力式の文字をひとつずつ見て、それが数値なのか、//
        // 演算子なのか、カッコなのかを判別して、優先順位をつける処理）
        for(int i = 0; i < expression.length; i++) {
            String chr;
            chr = expression[i];

            //演算子だったら走らせたい処理
            if(chr.equals("+") || chr.equals("-") || chr.equals("*") || chr.equals("/")) {
                operator[opCnt] = chr;
                //優先順位の判別
                if(chr.equals("+") || chr.equals("-")) {
                    priority[opCnt] = nest + 1;
                } else {
                    priority[opCnt] = nest + 2;
                }
                // 演算子の個数を数えてる
                opCnt = opCnt + 1;
                // 初期化
                value[opCnt] = 0;
                continue;
            }

            if(chr.equals("(")) {
                nest = nest + 10;
                continue;
            }

            if(chr.equals(")")) {
                nest = nest - 10;
                continue;
            }

            //数字だったときに走らせたい処理
            int num = Integer.parseInt(chr);
            if(0 <= num && num <= 9) {
                value[opCnt] = 10 * value[opCnt] + num;
            }
        }

        // 計算処理
        while(opCnt > 0) {
            int ip = 0;
            //優先順位の配列（priority）の中で、最も優先順位の高い部分を探している。
            for(int i = 1; i < opCnt; i++) {
                if(priority[ip] < priority[i]){
                    ip = i;
                }
            }
            String chr = operator[ip];

            try {
                if(chr.equals("+")) {
                    value[ip] = value [ip] + value [ip + 1];
                }
                if(chr.equals("-")) {
                    value[ip] = value [ip] - value [ip + 1];
                }
                if(chr.equals("*")) {
                    value[ip] = value [ip] * value [ip + 1];
                }
                if(chr.equals("/")) {
                    value[ip] = value [ip] / value [ip + 1];
                }
            } catch(ArithmeticException e) {
                System.out.println("0除算です");
                return;
            }

            for(int i = ip + 1; i < opCnt; i++) {
                value[i] = value[i + 1];
                operator[i - 1] = operator[i];
                priority[i-1] = priority[i];
            }
            // 演算子を一個へらす
            opCnt = opCnt-1;
        }
        System.out.println(value[0]);
    }
}

