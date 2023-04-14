package csen1002.main.task6;

import java.sql.SQLOutput;
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

    public static boolean checkSubset(LinkedHashSet<String> a, LinkedHashSet<String> b) {
        for (String s : a) {
            if (!b.contains(s)) {
                return false;
            }
        }

        return true;
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
        String test = "S;Z;I;P;B;J;W#b;f;i;m;n;p;s#S/PZb,S,iBbB;Z/II,If,P;I/B,JZPP,SPnJS,SWsI,bBPb,iB;P/JWWfP,S,Ss,e;B/e,pBPBb,sSP;J/BmPZ,Z,iP;W/bZ,mPnWb,pWBfB";
        CfgFirstFollow cfgFirstFollow = new CfgFirstFollow("S;W;G;A;D;C;P#f;h;l;o;q;s;t#S/A,DPS,DqDDq,qAlS,qDPPo;W/e,lSD,tCShP;G/C,G,S,sDC,sS;A/fPlDf,o;D/PS,WAPs,e,oW,qD;C/G,PDP,PW,W;P/o,q");
        cfgFirstFollow.follow();
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
                    int k = rule.length();

                    if (checkEpsilonInFirstB0ToK(rule, first)) {
                        if (!firstA.contains("e")) {
                            firstA.add("e");
                            isChanged = true;
                        }
                    }

                    for (int i = 0; i < k; i++) {

                        String ruleTillI = rule.substring(0, i);

                        if (checkEpsilonInFirstB0ToK(ruleTillI, first)) {
                            LinkedHashSet<String> firstBi = first.get(rule.charAt(i) + "");

                            LinkedHashSet<String> firstOfBiTemp = new LinkedHashSet<>(firstBi);
                            firstOfBiTemp.remove("e");

                            if (!checkSubset(firstOfBiTemp, firstA)) {
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

//        StringBuilder firstString = new StringBuilder();
//        for (String variable : variables) {
//            firstString.append(variable).append("/");
//            LinkedHashSet<String> firstA = first.get(variable);
//
//            List<String> firstAList = new ArrayList<>(firstA);
//            Collections.sort(firstAList);
//            firstA = new LinkedHashSet<>(firstAList);
//
//            for (String s : firstA) {
//                firstString.append(s);
//            }
//            firstString.append(";");
//        }
//
//        firstString.deleteCharAt(firstString.length() - 1);
//
//        return firstString.toString();
//        return first.toString();
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
                                beta = rule.charAt(indexOfBeta) + "";
                            }

                            LinkedHashSet<String> firstBetaCopy = new LinkedHashSet<>(first.get(beta));
                            LinkedHashSet<String> followB = follow.get(B);

                            firstBetaCopy.remove("e");

                            if (!checkSubset(firstBetaCopy, followB)) {
                                followB.addAll(firstBetaCopy);
                                isChanged = true;
                            }

                            if (checkEpsilonInFirstB0ToK(rule.substring(indexOfBeta), first)) {
                                LinkedHashSet<String> followA = follow.get(A);
                                if (!checkSubset(followA, followB)) {

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
