package csen1002.main.task6;

import java.util.*;

/**
 * Write your info here
 *
 * @name Mohamed Hossam
 * @id 46-9261
 * @labNumber 16
 */

public class CfgFirstFollow {
    HashMap<String, LinkedHashSet<String>> rules;
    LinkedHashSet<String> variables;
    LinkedList<Character> terminals;

    HashMap<String, LinkedHashSet<String>> first = new HashMap<>();


    /**
     * Constructs a Context Free Grammar
     *
     * @param cfg A formatted string representation of the CFG. The string
     *            representation follows the one in the task description
     */
    public CfgFirstFollow(String cfg) {
        rules = new HashMap<>();
        String[] parts = cfg.split("#");

        String[] variables = parts[0].split(";");
        this.variables = new LinkedHashSet<>(Arrays.asList(variables));

        String[] terminals = parts[1].split(";");
        this.terminals = new LinkedList<>();
        for (String t : terminals) {
            this.terminals.add(t.charAt(0));
        }

        String[] rulesForEachVariable = parts[2].split(";");
        for (String rule : rulesForEachVariable) {
            String variable = rule.charAt(0) + "";

            String[] variableRules = rule.substring(2).split(",");
            LinkedHashSet<String> variableRulesList = new LinkedHashSet<>();

            Collections.addAll(variableRulesList, variableRules);
            rules.put(variable, variableRulesList);
        }

        calculateFirst();
    }

    public static boolean isNotSubset(LinkedHashSet<String> a, LinkedHashSet<String> b) {
        for (String s : a) {
            if (!b.contains(s)) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkEpsilonInFirstB0ToK(String rule, HashMap<String, LinkedHashSet<String>> first) {
        for (int i = 0; i < rule.length(); i++) {

            LinkedHashSet<String> firstBi = first.get(rule.charAt(i) + "");
            if (!firstBi.contains("e")) {
                return false;
            }
        }

        return true;
    }

    public static int[] getIndicesOfOccurrence(String str, char target) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == target) {
                indices.add(i);
            }
        }
        int[] result = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            result[i] = indices.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        String test = "S;W;G;A;D;C;P#f;h;l;o;q;s;t#S/A,DPS,DqDDq,qAlS,qDPPo;W/e,lSD,tCShP;G/C,G,S,sDC,sS;A/fPlDf,o;D/PS,WAPs,e,oW,qD;C/G,PDP,PW,W;P/o,q";
        String edgeCase = "S;A;B;C#a;b;c#S/SAB,SBC,e;A/aAa,e;B/bB,e;C/cC,e";
        CfgFirstFollow cfgFirstFollow = new CfgFirstFollow(edgeCase);

        String first = cfgFirstFollow.first();
        String follow = cfgFirstFollow.follow();

        System.out.println(first);
        System.out.println(follow);
    }

    public void calculateFirst() {
        for (Character terminal : terminals) {
            LinkedHashSet<String> firstSet = new LinkedHashSet<>();
            firstSet.add(terminal + "");
            first.put(terminal + "", firstSet);
        }

        first.put("e", new LinkedHashSet<>(Collections.singletonList("e")));

        for (String variable : variables) {
            LinkedHashSet<String> firstA = new LinkedHashSet<>();
            first.put(variable, firstA);
        }
        boolean isChanged = true;

        while (isChanged) {
            isChanged = false;
            for (String variable : variables) {
                for (String rule : rules.get(variable)) {

                    LinkedHashSet<String> firstA = first.get(variable);

                    if (checkEpsilonInFirstB0ToK(rule, first)) {
                        if (!firstA.contains("e")) {
                            firstA.add("e");
                            isChanged = true;
                        }
                    }

                    int k = rule.length();

                    for (int i = 0; i < k; i++) {

                        String ruleTillI = rule.substring(0, i);



                        if (checkEpsilonInFirstB0ToK(ruleTillI, first)) {



                            LinkedHashSet<String> firstBi = first.get(rule.charAt(i) + "");

                            LinkedHashSet<String> firstOfBiTemp = new LinkedHashSet<>(firstBi);
                            firstOfBiTemp.remove("e");

                            if (isNotSubset(firstOfBiTemp, firstA)) {
                                if(i==0){
                                    System.out.println("here");
                                }
                                firstA.addAll(firstOfBiTemp);
                                isChanged = true;
                            }

                        }
                    }
                }

            }
        }
//        return first.toString();
    }

    public String getStringDecodingForFirstOrFollowSets(HashMap<String, LinkedHashSet<String>> set) {
        StringBuilder string = new StringBuilder();
        for (String variable : variables) {
            string.append(variable).append("/");
            LinkedHashSet<String> A = set.get(variable);

            List<String> AList = new ArrayList<>(A);
            Collections.sort(AList);
            A = new LinkedHashSet<>(AList);

            for (String s : A) {
                string.append(s);
            }
            string.append(";");
        }

        string.deleteCharAt(string.length() - 1);

        return string.toString();
    }

    /**
     * Calculates the First Set of each variable in the CFG.
     *
     * @return A string representation of the First of each variable in the CFG,
     * formatted as specified in the task description.
     */
    public String first() {
        return getStringDecodingForFirstOrFollowSets(first);
    }

    /**
     * Calculates the Follow Set of each variable in the CFG.
     *
     * @return A string representation of the Follow of each variable in the CFG,
     * formatted as specified in the task description.
     */
    public String follow() {
        HashMap<String, LinkedHashSet<String>> follow = new HashMap<>();
//        HashMap<String, LinkedHashSet<String>> first =
        for (String variable : variables) {
            LinkedHashSet<String> followA = new LinkedHashSet<>();
            follow.put(variable, followA);
        }

        follow.get("S").add("$");

        boolean isChanged = true;

        while (isChanged) {
            isChanged = false;
            for (String B : variables) {
                for (String A : variables) {
                    for (String rule : rules.get(A)) {
                        if (!rule.contains(B)) {
                            continue;
                        }

                        int[] indicesOfAllB = getIndicesOfOccurrence(rule, B.charAt(0));

                        for (int indexOfB : indicesOfAllB) {
                            int indexOfBeta = indexOfB + 1;
                            String beta = "e";

                            if (indexOfBeta < rule.length()) {
                                beta = rule.substring(indexOfBeta);
                            }

                            LinkedHashSet<String> firstBetaCopy = new LinkedHashSet<>(first.get(beta.charAt(0) + ""));
                            LinkedHashSet<String> followB = follow.get(B);

                            firstBetaCopy.remove("e");

                            if (isNotSubset(firstBetaCopy, followB)) {
                                followB.addAll(firstBetaCopy);
                                isChanged = true;
                            }

//                            System.out.println(beta);
                            if (beta.length() >= 2) {
                                if (first.get(beta.charAt(0) + "").contains("e")) {

                                    LinkedHashSet<String> firstBeta1Copy = new LinkedHashSet<>(first.get(beta.charAt(1) + ""));
                                    firstBeta1Copy.remove("e");

                                    if (isNotSubset(firstBeta1Copy, followB)) {
                                        followB.addAll(firstBeta1Copy);
                                        isChanged = true;
                                    }

                                }
                            }

                            if (checkEpsilonInFirstB0ToK(rule.substring(indexOfBeta), first)) {
                                LinkedHashSet<String> followA = follow.get(A);
                                if (isNotSubset(followA, followB)) {

                                    followB.addAll(followA);
                                    if (B.equals("W")) {
                                        System.out.println("W was here");
                                        System.out.println(followB);
                                    }
                                    isChanged = true;
                                }
                            }
                        }


                    }
                }
            }
        }

        return getStringDecodingForFirstOrFollowSets(follow);
    }


}
