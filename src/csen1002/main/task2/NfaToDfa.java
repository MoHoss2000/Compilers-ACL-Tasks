package csen1002.main.task2;

import java.util.*;


/**
 * Write your info here
 *
 * @name Mohamed Hossam
 * @id 46-9261
 * @labNumber 16
 */

public class NfaToDfa {

    private final NFA nfa;
    TreeSet<DfaCombinedState> dfaStates;
    TreeSet<DfaStateTransition> dfaTransitions;
    DfaCombinedState startState;
    TreeSet<DfaCombinedState> acceptStates;

    /**
     * Constructs a DFA corresponding to an NFA
     *
     * @param input A formatted string representation of the NFA for which an
     *              equivalent DFA is to be constructed. The string representation
     *              follows the one in the task description
     */
    public NfaToDfa(String input) {
        String[] splitted = input.split("#");
        TreeSet<Integer> states = new TreeSet<>();

        for (String state : splitted[0].split(";")) {
            states.add(Integer.parseInt(state));
        }

        splitted[1] = splitted[1].replace(";", "");
        char[] alphabet = splitted[1].toCharArray();

        TreeSet<NfaStateTransition> transitions = new TreeSet<>();

        for (String transitionString : splitted[2].split(";")) {
            transitions.add(new NfaStateTransition(transitionString));
        }

        int startState = Integer.parseInt(splitted[3]);

        TreeSet<Integer> acceptStates = new TreeSet<>();

        for (String acceptState : splitted[4].split(";")) {
            acceptStates.add(Integer.parseInt(acceptState));
        }

        nfa = new NFA(states, alphabet, transitions, startState, acceptStates);
        dfaStates = new TreeSet<>();
        dfaTransitions = new TreeSet<>();

        convertToDfa();
    }

    public void convertToDfa() {
        Hashtable<Integer, DfaCombinedState> epsilonClosureTable = getEpsilonClosureTable();

        startState = epsilonClosureTable.get(nfa.startState);
        dfaStates.add(startState);


        Stack<DfaCombinedState> stack = new Stack<DfaCombinedState>();
        stack.push(startState);

        DeadState deadState = null;

        while (!stack.isEmpty()) {
            DfaCombinedState currentState = stack.pop();

            for (char symbol : nfa.alphabet) {
                DfaCombinedState dfaState = getReachableStates(currentState, symbol, epsilonClosureTable);

                // if the state is empty, then it is a dead state
                if (dfaState.states.isEmpty()) {

                    // if the dead state is not created yet, then create it
//                    and add its transitions
                    if (deadState == null) {
                        deadState = new DeadState();

                        for (char deadSymbol : nfa.alphabet) {
                            dfaTransitions.add(new DfaStateTransition(deadState, deadState, deadSymbol));
                        }
                    }

                    dfaTransitions.add(new DfaStateTransition(currentState, deadState, symbol));
                    continue;
                }

                boolean contains = false;
                for (DfaCombinedState state : dfaStates) {
                    if (state.equals(dfaState)) {
                        dfaState = state;
                        contains = true;
                        break;
                    }
                }

                if (!contains) {
                    dfaStates.add(dfaState);
                    stack.push(dfaState);
                }

                dfaTransitions.add(new DfaStateTransition(currentState, dfaState, symbol));
            }
        }

        if (deadState != null) {
            dfaStates.add(deadState);
        }

        acceptStates = new TreeSet<>();

        for (DfaCombinedState dfaState : dfaStates) {
            for (int state : dfaState.states) {
                if (nfa.acceptStates.contains(state)) {
                    acceptStates.add(dfaState);
                    break;
                }
            }
        }
    }

    private DfaCombinedState getReachableStates(DfaCombinedState dfaState, char symbol, Hashtable<Integer, DfaCombinedState> epsilonClosureTable) {
        TreeSet<Integer> reachableStates = new TreeSet<>();

        for (int state : dfaState.states) {
            for (NfaStateTransition transition : nfa.transitions) {
                if (transition.sourceState == state && transition.transitionSymbol == symbol) {
                    DfaCombinedState epsilonClosure = epsilonClosureTable.get(transition.destinationState);
                    reachableStates.addAll(epsilonClosure.states);
                }

            }
        }

        return new DfaCombinedState(reachableStates);
    }

    public Hashtable<Integer, DfaCombinedState> getEpsilonClosureTable() {
        Hashtable<Integer, DfaCombinedState> epsilonClosure = new Hashtable<>();

        for (int state : nfa.states) {
            epsilonClosure.put(state, getEpsilonClosure(state));
        }

        return epsilonClosure;
    }

    public DfaCombinedState getEpsilonClosure(int state) {
        TreeSet<Integer> epsilonClosure = new TreeSet<>();
        epsilonClosure.add(state);

        Stack<Integer> stack = new Stack<>();
        stack.push(state);

        while (!stack.isEmpty()) {
            int currentState = stack.pop();

            for (NfaStateTransition transition : nfa.transitions) {
                if (transition.sourceState == currentState && transition.transitionSymbol == 'e') {
                    if (!epsilonClosure.contains(transition.destinationState)) {
                        epsilonClosure.add(transition.destinationState);
                        stack.push(transition.destinationState);
                    }
                }
            }
        }

        return new DfaCombinedState(epsilonClosure);
    }


    /**
     * @return Returns a formatted string representation of the DFA. The string
     * representation follows the one in the task description
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (DfaCombinedState state : dfaStates) {
            sb.append(state).append(';');
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append('#');

        for (char symbol : nfa.alphabet) {
            sb.append(symbol).append(';');
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append('#');

        for (DfaStateTransition transition : dfaTransitions) {
            sb.append(transition.toString()).append(';');
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append('#');

        sb.append(startState).append('#');

        for (DfaCombinedState state : acceptStates) {
            sb.append(state).append(';');
        }

        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public static class DeadState extends DfaCombinedState {
        public DeadState() {
            super(new TreeSet<Integer>(List.of(-1)));
        }
    }

    public static class DfaCombinedState implements Comparable<DfaCombinedState> {
        boolean isVisited;
        private final TreeSet<Integer> states;

        public DfaCombinedState(TreeSet<Integer> states) {
            this.isVisited = false;
            this.states = states;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int state : states) {
                sb.append(state).append('/');
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        public boolean equals(Object o) {
            if (o instanceof DfaCombinedState other) {
                return this.states.equals(other.states);
            } else {
                return false;
            }
        }

        @Override
        public int compareTo(DfaCombinedState o) {
            if (this.states.size() == 0) {
                return -1;
            } else if (o.states.size() == 0) {
                return 1;
            } else {
                int thisFirst = this.states.first();
                int oFirst = o.states.first();
                if (thisFirst != oFirst) {
                    return Integer.compare(thisFirst, oFirst);
                } else {
                    TreeSet<Integer> thisTail = new TreeSet<Integer>(this.states);
                    thisTail.remove(thisFirst);

                    TreeSet<Integer> oTail = new TreeSet<Integer>(o.states);
                    oTail.remove(oFirst);

                    return new DfaCombinedState(thisTail).compareTo(new DfaCombinedState(oTail));
                }
            }
        }
    }

    private static class DfaStateTransition implements Comparable<DfaStateTransition> {
        private final DfaCombinedState destinationState;
        private final char transitionSymbol;
        private final DfaCombinedState sourceState;

        public DfaStateTransition(DfaCombinedState sourceState, DfaCombinedState destinationState, char transitionSymbol) {
            this.sourceState = sourceState;
            this.destinationState = destinationState;
            this.transitionSymbol = transitionSymbol;
        }

        public String toString() {
            return sourceState + "," + transitionSymbol + "," + destinationState;
        }

        @Override
        public int compareTo(DfaStateTransition o) {
            if (this.sourceState != o.sourceState) {
                return this.sourceState.compareTo(o.sourceState);
            } else if (this.transitionSymbol != o.transitionSymbol) {
                return Character.compare(this.transitionSymbol, o.transitionSymbol);
            } else {
                return this.destinationState.compareTo(o.destinationState);
            }
        }
    }

    private static class NfaStateTransition implements Comparable<NfaStateTransition> {
        private final int destinationState;
        private final char transitionSymbol;
        private final int sourceState;

        public NfaStateTransition(String string) {
            String[] parts = string.split(",");
            sourceState = Integer.parseInt(parts[0]);
            transitionSymbol = parts[1].charAt(0);
            destinationState = Integer.parseInt(parts[2]);
        }


        public String toString() {
            return sourceState + "," + transitionSymbol + "," + destinationState;
        }

        @Override
        public int compareTo(NfaStateTransition o) {
            if (this.sourceState != o.sourceState) {
                return Integer.compare(this.sourceState, o.sourceState);
            } else if (this.transitionSymbol != o.transitionSymbol) {
                return Character.compare(this.transitionSymbol, o.transitionSymbol);
            } else {
                return Integer.compare(this.destinationState, o.destinationState);
            }
        }
    }

    private static class NFA {
        TreeSet<Integer> states;
        char[] alphabet;
        TreeSet<NfaStateTransition> transitions;
        int startState;
        TreeSet<Integer> acceptStates;


        public NFA(TreeSet<Integer> states, char[] alphabet, TreeSet<NfaStateTransition> transitions, int initialState, TreeSet<Integer> acceptStates) {
            this.states = states;
            this.alphabet = alphabet;
            this.transitions = transitions;
            this.startState = initialState;
            this.acceptStates = acceptStates;
        }

        @Override
        public String toString() {

            StringBuilder sb = new StringBuilder();

            // Append set of states
            for (int state : states) {
                sb.append(state).append(';');
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append('#');

            // Append alphabet
            for (char symbol : alphabet) {
                sb.append(symbol).append(';');
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append('#');

            // Append transitions
            for (NfaStateTransition transition : transitions) {
                sb.append(transition.toString()).append(';');
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append('#');

            // Append start state
            sb.append(startState).append('#');

            // Append accept states
            for (int state : acceptStates) {
                sb.append(state).append(';');
            }

            sb.deleteCharAt(sb.length() - 1);


            return sb.toString();
        }
    }

}
